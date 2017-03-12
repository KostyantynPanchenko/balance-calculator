package com.softserveinc.balance_calculator.api.filters;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserveinc.balance_calculator.service.RegisterService;
import com.softserveinc.balance_calculator.service.StoreService;
import com.softserveinc.balance_calculator.service.exception.EntityNotFoundServiceException;
import com.softserveinc.balance_calculator.service.exception.ServiceException;

import io.dropwizard.jersey.errors.ErrorMessage;
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
@Provider
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
    private final String FORBIDDEN = "Unauthorized request to store id=%d by tenantId=%d!";
    private final String STORE_NOT_FOUND = "Store with id=%d not found.";
    private final String REGISTER_NOT_FOUND = "Register with id=%d not found in store No%d.";
    private final String SERVER_ERROR = "Error occurred while trying to retrieve store with id=%d.";
    private final String AUTHORIZATION_REQUIRED = "Authorization required. Token not found.";
    
    public AuthorizationFilter(StoreService storeService, RegisterService registerService) {
        this.storeService = storeService;
        this.registerService = registerService;
    }
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (isStorePostRequest(requestContext)) {
            filterStorePostRequest(requestContext);
        } else if (isStoreGetPutDeleteRequest(requestContext) || isRegisterPostRequest(requestContext)) {
            filterStoreGetPutDeleteRequest(requestContext);
        } else if (isRegisterGetPutDeleteRequest(requestContext) || isPostToContributions(requestContext)) {
            filterRegisterGetPutDeleteRequest(requestContext);
        } else {
            System.out.println("NOT ALLOWED");
            throw new NotAllowedException(Response.status(Status.METHOD_NOT_ALLOWED).build());
        }
    }

    /**
     * Filters POST requests to <code>Store</code>.
     *  
     * @param requestContext current <code>ContainerRequestContext</code>
     * @throws IOException 
     * @throws URISyntaxException 
     */
    private void filterStorePostRequest(ContainerRequestContext requestContext) throws IOException {
        try {
            Long tenantId = getTenantIdFromJwt(requestContext);
            if (tenantId == null) {
                throwWebApplicationException(AUTHORIZATION_REQUIRED, Status.UNAUTHORIZED);
            }
            URI newUri = new URI(requestContext.getUriInfo().getAbsolutePath().toString() + "?tenantId=" + tenantId.toString());
            requestContext.setRequestUri(newUri);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException 
                | UnsupportedEncodingException | IllegalArgumentException jwtEx) {
            throwWebApplicationException(jwtEx.getMessage(), Status.UNAUTHORIZED);
        } catch (URISyntaxException e) {
            throwWebApplicationException(e.getMessage(), Status.BAD_REQUEST);
        }
    }

    /**
     * Filters GET, PUT and DELETE requests to <code>Store</code>.
     *  
     * @param requestContext current <code>ContainerRequestContext</code>
     */
    private void filterStoreGetPutDeleteRequest(ContainerRequestContext requestContext) {
        try {
            Long pathStoreId = getStoreIdFromPath(requestContext);
            Long jwtTenantId = getTenantIdFromJwt(requestContext);
            
            try {
                if (!isAuthorizedRequestToStore(getTenantId(pathStoreId), jwtTenantId)) {
                    throwWebApplicationException(String.format(FORBIDDEN, new Object[] {pathStoreId, jwtTenantId}), Status.FORBIDDEN);
                }
            } catch (EntityNotFoundServiceException notFound) {
                throwWebApplicationException(String.format(STORE_NOT_FOUND, pathStoreId), Status.NOT_FOUND);
            } catch (ServiceException e) {
                throwWebApplicationException(String.format(STORE_NOT_FOUND, pathStoreId), Status.INTERNAL_SERVER_ERROR);
            }
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException 
                | UnsupportedEncodingException | IllegalArgumentException jwtEx) {
            throwWebApplicationException(jwtEx.getMessage(), Status.UNAUTHORIZED);
        }
    }
    
    /**
     * Filters GET, PUT and DELETE requests to <code>Store</code>.
     *  
     * @param requestContext current <code>ContainerRequestContext</code>
     */
    private void filterRegisterGetPutDeleteRequest(ContainerRequestContext requestContext) {
        try {
            Long jwtTenantId = getTenantIdFromJwt(requestContext);
            Long pathStoreId = getStoreIdFromPath(requestContext);
            Long pathRegisterId = getRegisterIdFromPath(requestContext);
            
            try {
                if (!isAuthorizedRequestToStore(getTenantId(pathStoreId), jwtTenantId)) {
                    throwWebApplicationException(String.format(FORBIDDEN, new Object[] {pathStoreId, jwtTenantId}), Status.FORBIDDEN);
                }
                if (!isAuthorizedRequestToRegister(pathStoreId, pathRegisterId)) {
                    throwWebApplicationException(String.format(FORBIDDEN, new Object[] {pathStoreId, pathRegisterId}), Status.FORBIDDEN);
                }
            } catch (EntityNotFoundServiceException notFound) {
                throwWebApplicationException(String.format(REGISTER_NOT_FOUND, pathRegisterId, pathStoreId), Status.NOT_FOUND);
            } catch (ServiceException e) {
                throwWebApplicationException(String.format(SERVER_ERROR, pathStoreId), Status.INTERNAL_SERVER_ERROR);
            }
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException 
                | UnsupportedEncodingException | IllegalArgumentException jwtEx) {
            throwWebApplicationException(jwtEx.getMessage(), Status.UNAUTHORIZED);
        }
    }

    private void throwWebApplicationException(String message, Status status) {
        LOGGER.error(message);
        throw new WebApplicationException(message, status);
    }

    private boolean isStorePostRequest(ContainerRequestContext requestContext) {
        return getPathSegmentsSize(requestContext) == 1 && isPost(requestContext);
    }

    private boolean isStoreGetPutDeleteRequest(ContainerRequestContext requestContext) {
        return getPathSegmentsSize(requestContext) == 2 && isGetOrPutOrDelete(requestContext);
    }

    private boolean isRegisterPostRequest(ContainerRequestContext requestContext) {
        return getPathSegmentsSize(requestContext) == 3 && isGetOrPutOrDelete(requestContext);
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
     * @throws ServiceException if <code>Store</code> entity with specified id was not found
     */
    private Long getTenantId(Long pathStoreId) throws ServiceException {
        return storeService.getStoreById(pathStoreId).getTenantId();
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
     * @throws ServiceException 
     * @throws EntityNotFoundServiceException 
     */
    private boolean isAuthorizedRequestToRegister(Long storeId, Long registerId) throws EntityNotFoundServiceException, ServiceException {
        return registerService.getRegisterById(storeId, registerId).getStoreId().equals(storeId);
    }
    
    /**
     * Retrieves tenant id from JWT.
     * 
     * @param requestContext current <code>ContainerRequestContext</code>
     * @return tenant id
     * @throws ExpiredJwtException if JWT expired
     * @throws UnsupportedJwtException if JWT is not supported
     * @throws MalformedJwtException in case of malformed JWT
     * @throws SignatureException if JWT signature not correct
     * @throws IllegalArgumentException if could not parse long value string
     * @throws UnsupportedEncodingException if JWT encrypted with unsupported algorithm
     */
    private Long getTenantIdFromJwt(ContainerRequestContext requestContext) throws ExpiredJwtException, UnsupportedJwtException,
        MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
        
        String jwt = extractJwtFromRequest(requestContext);
        
        return Long.valueOf(
                (String) Jwts.parser()
                .setSigningKey(KEY.getBytes(CHARSET_NAME))
                .parseClaimsJws(jwt)
                .getBody()
                .get(TENANT_ID));
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
            throw new NotAuthorizedException(Response.status(Status.UNAUTHORIZED)
                    .entity(new ErrorMessage(401, AUTHORIZATION_REQUIRED)).build());
        }
        return header.substring(header.indexOf(DELIMITER) + 1);
    }

}
