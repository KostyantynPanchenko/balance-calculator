package com.softserveinc.balance.calculator.api.filters;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserveinc.balance.calculator.dto.RegisterDTO;
import com.softserveinc.balance.calculator.dto.StoreDTO;
import com.softserveinc.balance.calculator.service.RegisterService;
import com.softserveinc.balance.calculator.service.StoreService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * Authorization filter. Checks if request contain JWT and tenant can access requested store.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 07/03/2017
 *
 */
@PreMatching
public class AuthorizationFilter implements ContainerRequestFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(AuthorizationFilter.class);
    private final StoreService storeService;
    private final RegisterService registerService;
    private final int STORE_ID_PATH_POSITION = 1;
    private final int REGISTER_ID_PATH_POSITION = 3;
    private final String KEY = "GodSaveTheQuin";
    private final String DELIMITER = " ";
    private final String CHARSET_NAME = "UTF-8";
    private final String TENANT_ID = "tenantId";
    private final String FORBIDDEN_S = "Unauthorized request to store id=%d by tenantId=%d";
    private final String FORBIDDEN_R = "Unauthorized request to register id=%d by tenantId=%d";
    private final String STORE_NOT_FOUND = "Store with id=%d not found";
    private final String REGISTER_NOT_FOUND = "Register with id=%d not found in store No%d";
    
    public AuthorizationFilter(StoreService storeService, RegisterService registerService) {
        this.storeService = storeService;
        this.registerService = registerService;
    }
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Long tenantId = getTenantIdFromJwt(requestContext);
        
        if (isHealthcheckRequest(requestContext)) {
            // OK, process 
        } else if (isStorePostRequest(requestContext)) {
            filterStorePostRequest(tenantId, requestContext);
        } else if (isStoreGetPutDeleteRequest(requestContext) || isRegisterPostRequest(requestContext)) {
            filterStoreGetPutDeleteRequest(tenantId, requestContext); 
        } else if (isRegisterGetPutDeleteRequest(requestContext) || isPostToContributions(requestContext)) {
            filterRegisterGetPutDeleteRequest(tenantId, requestContext);
        } else {
            throw new NotAllowedException(Response.status(Status.METHOD_NOT_ALLOWED).build());
        }
    }

    /**
     * Filters POST requests to <code>Store</code>.
     * @param tenantId tenant id extracted from jwt
     *  
     * @param requestContext current <code>ContainerRequestContext</code>
     * @throws URISyntaxException if could not construct URI
     */
    private void filterStorePostRequest(Long tenantId, ContainerRequestContext requestContext) {
        try {
            URI newUri = new URI(requestContext.getUriInfo().getAbsolutePath().toString() + "?tenantId=" + tenantId.toString());
            requestContext.setRequestUri(newUri);
        } catch (URISyntaxException e) {
            throwWebApplicationException(e.getMessage(), Status.BAD_REQUEST);
        }
        
    }

    /**
     * Filters GET, PUT and DELETE requests to <code>Store</code>.
     * @param tenantId tenant id extracted from jwt
     *  
     * @param requestContext current <code>ContainerRequestContext</code>
     */
    private void filterStoreGetPutDeleteRequest(Long tenantId, ContainerRequestContext requestContext) {
        try {
            Long pathStoreId = getStoreIdFromPath(requestContext);
            
            if (!isAuthorizedRequestToStore(getTenantId(pathStoreId), tenantId)) {
                throwForbiddenException(String.format(FORBIDDEN_S, new Object[] {pathStoreId, tenantId}));
            }
            URI newUri = new URI(requestContext.getUriInfo().getAbsolutePath().toString() + "?tenantId=" + tenantId.toString());
            requestContext.setRequestUri(newUri);
        } catch (URISyntaxException e) {
            throwWebApplicationException(e.getMessage(), Status.BAD_REQUEST);
        }
    }
    
    /**
     * Filters GET, PUT and DELETE requests to <code>Store</code>.
     * @param tenantId tenant id extracted from jwt
     *  
     * @param requestContext current <code>ContainerRequestContext</code>
     */
    private void filterRegisterGetPutDeleteRequest(Long tenantId, ContainerRequestContext requestContext) {
        Long pathStoreId = getStoreIdFromPath(requestContext);
        Long pathRegisterId = getRegisterIdFromPath(requestContext);
        
        if (!isAuthorizedRequestToStore(getTenantId(pathStoreId), tenantId)) {
            throwForbiddenException(String.format(FORBIDDEN_S, new Object[] {pathStoreId, tenantId}));
        }
        if (!isAuthorizedRequestToRegister(pathStoreId, pathRegisterId)) {
            throwForbiddenException(String.format(FORBIDDEN_R, new Object[] {pathRegisterId, tenantId}));
        }
    }

    private void throwForbiddenException(String message) {
        LOGGER.error(message);
        throw new ForbiddenException(message);
    }

    private void throwNotFoundException(String message) {
        LOGGER.error(message);
        throw new NotFoundException(message);
    }
    
    private void throwWebApplicationException(String message, Status status) {
        LOGGER.error(message);
        throw new WebApplicationException(message, status);
    }

    private boolean isHealthcheckRequest(ContainerRequestContext requestContext) {
        return requestContext.getUriInfo().getPath().toString().equals("/healthcheck");
    }

    private boolean isStorePostRequest(ContainerRequestContext requestContext) {
        return getPathSegmentsSize(requestContext) == 1 && isPost(requestContext);
    }

    private boolean isStoreGetPutDeleteRequest(ContainerRequestContext requestContext) {
        return getPathSegmentsSize(requestContext) == 2 && isGetOrPutOrDelete(requestContext);
    }

    private boolean isRegisterPostRequest(ContainerRequestContext requestContext) {
        return getPathSegmentsSize(requestContext) == 3 && isPost(requestContext);
    }

    private boolean isRegisterGetPutDeleteRequest(ContainerRequestContext requestContext) {
        return getPathSegmentsSize(requestContext) >= 4 && isGetOrPutOrDelete(requestContext);
    }

    private boolean isPostToContributions(ContainerRequestContext requestContext) {
        return isPost(requestContext) && getPathSegmentsSize(requestContext) == 5;
    }

    private boolean isPost(ContainerRequestContext requestContext) {
        return requestContext.getMethod().equals(HttpMethod.POST);
    }

    private boolean isGetOrPutOrDelete(ContainerRequestContext requestContext) {
        String method = requestContext.getMethod();
        return method.equals(HttpMethod.GET) || method.equals(HttpMethod.PUT) || method.equals(HttpMethod.DELETE);
    }

    private int getPathSegmentsSize(ContainerRequestContext requestContext) {
        return requestContext.getUriInfo().getPathSegments().size();
    }

    /**
     * Extracts store id from request URI.
     * 
     * @param requestContext current <code>ContainerRequestContext</code>
     * @return <code>Long</code> value of store id or <code>null</code> if POST request
     * to create new <code>Store</code>
     */
    private Long getStoreIdFromPath(ContainerRequestContext requestContext) {
        return Long.valueOf(requestContext.getUriInfo().getPathSegments().get(STORE_ID_PATH_POSITION).toString());
    }
    
    /**
     * Extracts register id from request URI.
     * 
     * @param requestContext current <code>ContainerRequestContext</code>
     * @return <code>Long</code> value of register id or <code>null</code> if POST request
     * to create new <code>Register</code>
     */
    private Long getRegisterIdFromPath(ContainerRequestContext requestContext) {
        return Long.valueOf(requestContext.getUriInfo().getPathSegments().get(REGISTER_ID_PATH_POSITION).toString());
    }

    /**
     * Retrieves tenant id for specified store id.
     * 
     * @param pathStoreId store id extracted from request URI
     * @return <code>Long</code> value of tenant id 
     */
    private Long getTenantId(Long pathStoreId) {
        StoreDTO store = storeService.getStoreById(pathStoreId);
        if (store == null) {
            throwNotFoundException(String.format(STORE_NOT_FOUND, pathStoreId));
        }
        return store.getTenantId();
    }

    /**
     * Checks if tenant id extracted from JWT matches tenant id extracted from <code>Store</code>
     * entity with id specified in request URI.
     * 
     * @param tenantId tenant id extracted from <code>Store</code> entity
     * @param jwtTenantId tenant id extracted from JWT
     * @return true if tenant id specified in JWT matches tenant id in store with specified id; false otherwise
     */
    private boolean isAuthorizedRequestToStore(Long tenantId, Long jwtTenantId) {
        return tenantId.equals(jwtTenantId);
    }

    /**
     * Checks if tenant id extracted from JWT matches tenant id extracted from <code>Store</code>
     * entity with id specified in request URI.
     * 
     * @param tenantId tenant id extracted from <code>Store</code> entity
     * @param jwtTenantId tenant id extracted from JWT
     * @return true if tenant id specified in JWT matches tenant id in store with specified id; false otherwise
     */
    private boolean isAuthorizedRequestToRegister(Long storeId, Long registerId) {
        RegisterDTO register = registerService.getRegisterById(registerId);
        if (register == null) {
            throwNotFoundException(String.format(REGISTER_NOT_FOUND, registerId, storeId));
        }
        return register.getStoreId().equals(storeId);
    }
    
    /**
     * Retrieves tenant id from JWT.
     * 
     * @param requestContext current <code>ContainerRequestContext</code>
     * @return tenant id
     */
    private Long getTenantIdFromJwt(ContainerRequestContext requestContext) {
        String jwt = extractJwtFromRequest(requestContext);
        Integer id = null;
        try {
            id = (Integer) Jwts.parser()
                    .setSigningKey(KEY.getBytes(CHARSET_NAME))
                    .parseClaimsJws(jwt)
                    .getBody()
                    .get(TENANT_ID);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
                | IllegalArgumentException | UnsupportedEncodingException e) {
            throw new NotAuthorizedException("Bearer");
        }
        return new Long(id.longValue());
    }

    /**
     * Extracts <code>String</code> representation of JWT from Authorization header.
     * 
     * @param requestContext current <code>ContainerRequestContext</code>
     * @return <code>String</code> representation of JWT
     */
    private String extractJwtFromRequest(ContainerRequestContext requestContext) {
        String header = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (header == null) {
            throw new NotAuthorizedException("Bearer");
        }
        return header.substring(header.indexOf(DELIMITER) + 1);
    }

}
