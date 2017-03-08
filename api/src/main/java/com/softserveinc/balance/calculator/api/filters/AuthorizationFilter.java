package com.softserveinc.balance.calculator.api.filters;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserveinc.balance.calculator.service.StoreService;
import com.softserveinc.balance.calculator.service.exception.EntityNotFoundServiceException;
import com.softserveinc.balance.calculator.service.exception.ServiceException;

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
public class AuthorizationFilter implements ContainerRequestFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(AuthorizationFilter.class);
    private final int STORE_ID_PATH_POSITION = 1;
    private final StoreService storeService;
    private final String KEY = "GodSaveTheQuin";
    private final String DELIMITER = " ";
    private final String CHARSET_NAME = "UTF-8";
    private final String TENANT_ID = "tenantId";
    private final String UNAUTHORIZED = "Unauthorized request to store id=%d by tenantId=%d!";
    private final String NOT_FOUND = "Store with id=%d not found.";
    private final String SERVER_ERROR = "Error occurred while trying to retrieve store with id=%d.";
    private final String NO_AUTHORIZATION_HEADER = "Authorization header not found.";
    
    public AuthorizationFilter(StoreService storeService) {
        this.storeService = storeService;
    }
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (!isStorePostRequest(requestContext)) {
            try {
                Long pathStoreId = getStoreIdFromPath(requestContext);
                Long jwtTenantId = getTenantIdFromJwt(getJwt(requestContext));
                
                try {
                    if (!isAuthorized(getTenantId(pathStoreId), jwtTenantId)) {
                        String message = String.format(UNAUTHORIZED, new Object[] {pathStoreId, jwtTenantId});
                        LOGGER.error(message);
                        throw new NotAuthorizedException(message, Response.status(Status.UNAUTHORIZED));
                    }
                } catch (EntityNotFoundServiceException notFound) {
                    String message = String.format(NOT_FOUND, pathStoreId);
                    LOGGER.error(message);
                    throw new NotFoundException(message);
                } catch (ServiceException e) {
                    String message = String.format(SERVER_ERROR, pathStoreId);
                    LOGGER.error(message);
                    throw new ServerErrorException(message, Status.INTERNAL_SERVER_ERROR, e);
                }
            } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException 
                    | UnsupportedEncodingException | IllegalArgumentException jwtEx) {
                String message = jwtEx.getMessage();
                LOGGER.error(message, jwtEx);
                throw new BadRequestException(message);
            }
        }
    }
    
    /**
     * Checks if current request is POST request to create new <code>Store</code>.
     * 
     * @param requestContext current <code>ContainerRequestContext</code>     * 
     * @param segments list of current request path segments 
     * @return true if current request is POST request to create new <code>Store</code>
     */
    private boolean isStorePostRequest(ContainerRequestContext requestContext) {
        return requestContext.getMethod().equals(HttpMethod.POST) && requestContext.getUriInfo().getPathSegments().size() == 1;
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
     * Extracts <code>String</code> representation of JWT from Authorization header.
     * 
     * @param requestContext current <code>ContainerRequestContext</code>
     * @return <code>String</code> representation of JWT
     */
    private String getJwt(ContainerRequestContext requestContext) {
        String header = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (header == null) {
            throw new NotAuthorizedException(Response.status(Status.UNAUTHORIZED)
                    .entity(new ErrorMessage(401, NO_AUTHORIZATION_HEADER)).build());
        }
        return header.substring(header.indexOf(DELIMITER) + 1);
    }

    /**
     * Checks if tenant id extracted from JWT matches tenant id extracted from <code>Store</code>
     * entity with id specified in request URI.
     * 
     * @param tenantId tenant id extracted from <code>Store</code> entity
     * @param jwtTenantId tenant id extracted from JWT
     * @return true if tenant id specified in JWT matches tenant id in store with specified id; false otherwise
     */
    private boolean isAuthorized(Long tenantId, Long jwtTenantId) {
        return tenantId.equals(jwtTenantId);
    }
    
    /**
     * Retrieves tenant id from JWT.
     * 
     * @param jwt string representation of JWT
     * @return tenant id
     * @throws ExpiredJwtException if JWT expired
     * @throws UnsupportedJwtException if JWT is not supported
     * @throws MalformedJwtException in case of malformed JWT
     * @throws SignatureException if JWT signature not correct
     * @throws IllegalArgumentException if could not parse long value string
     * @throws UnsupportedEncodingException if JWT encrypted with unsupported algorithm
     */
    private Long getTenantIdFromJwt(String jwt) throws ExpiredJwtException, UnsupportedJwtException,
    MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
        return Long.valueOf((String) Jwts.parser()
                .setSigningKey(KEY.getBytes(CHARSET_NAME))
                .parseClaimsJws(jwt)
                .getBody()
                .get(TENANT_ID));
    }

}