package com.softserveinc.balance.calculator.api.filters;

import java.io.UnsupportedEncodingException;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;

import org.slf4j.Logger;

import com.softserveinc.balance.calculator.dto.StoreDTO;
import com.softserveinc.balance.calculator.service.RegisterService;
import com.softserveinc.balance.calculator.service.StoreService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * Abstract filter. Provide realization of common methods used in custom filters.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 07/03/2017
 *
 */
public abstract class AbstractFilter {

    protected final StoreService storeService;
    protected final RegisterService registerService;
    protected final String TENANT_ID = "tenantId";
    private final int STORE_ID_PATH_POSITION = 1;
    private final int REGISTER_ID_PATH_POSITION = 3;
    private final String KEY = "GodSaveTheQuin";
    private final String DELIMITER = " ";
    private final String CHARSET_NAME = "UTF-8";
    private final String STORE_NOT_FOUND = "Store with id=%d not found";
    
    protected AbstractFilter(StoreService storeService, RegisterService registerService) {
        this.storeService = storeService;
        this.registerService = registerService;
    }

    /**
     * Checks if request is a POST request.
     * 
     * @param requestContext current <code>ContainerRequestContext</code>
     * @return true if POST request
     */
    protected boolean isPostRequest(ContainerRequestContext requestContext) {
        return requestContext.getMethod().equals(HttpMethod.POST);
    }

    /**
     * Checks if tenant id extracted from JWT matches tenant id extracted from <code>Store</code>
     * entity with id specified in request URI.
     * 
     * @param tenantId tenant id extracted from <code>Store</code> entity
     * @param jwtTenantId tenant id extracted from JWT
     * @return true if tenant id specified in JWT matches tenant id in store with specified id; false otherwise
     */
    protected boolean isAuthorizedRequestToStore(Long tenantId, Long jwtTenantId) {
        return tenantId.equals(jwtTenantId);
    }

    /**
     * Extracts store id from request URI.
     * 
     * @param requestContext current <code>ContainerRequestContext</code>
     * @return <code>Long</code> value of store id or <code>null</code> if POST request
     * to create new <code>Store</code>
     */
    protected Long getStoreIdFromPath(ContainerRequestContext requestContext) {
        return Long.valueOf(requestContext.getUriInfo().getPathSegments().get(STORE_ID_PATH_POSITION).toString());
    }
    
    /**
     * Extracts register id from request URI.
     * 
     * @param requestContext current <code>ContainerRequestContext</code>
     * @return <code>Long</code> value of register id or <code>null</code> if POST request
     * to create new <code>Register</code>
     */
    protected Long getRegisterIdFromPath(ContainerRequestContext requestContext) {
        return Long.valueOf(requestContext.getUriInfo().getPathSegments().get(REGISTER_ID_PATH_POSITION).toString());
    }

    /**
     * Retrieves tenant id for specified store id.
     * 
     * @param pathStoreId store id extracted from request URI
     * @return <code>Long</code> value of tenant id 
     */
    protected Long getTenantId(Long pathStoreId) {
        StoreDTO store = storeService.getStoreById(pathStoreId);
        if (store == null) {
            throw new NotFoundException(String.format(STORE_NOT_FOUND, pathStoreId));
        }
        return store.getTenantId();
    }
    
    /**
     * Retrieves tenant id from JWT.
     * 
     * @param requestContext current <code>ContainerRequestContext</code>
     * @return tenant id
     */
    protected Long getTenantIdFromJwt(ContainerRequestContext requestContext) {
        String jwt = extractJwtFromHeader(requestContext);
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
        return id.longValue();
    }

    /**
     * Extracts <code>String</code> representation of JWT from Authorization header.
     * 
     * @param requestContext current <code>ContainerRequestContext</code>
     * @return <code>String</code> representation of JWT
     */
    protected String extractJwtFromHeader(ContainerRequestContext requestContext) {
        String header = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (header == null) {
            throw new NotAuthorizedException("Bearer");
        }
        return header.substring(header.indexOf(DELIMITER) + 1);
    }
    
    /**
     * Logs and throws new <code>ForbiddenException</code>
     * @param logger current logger
     * @param message message to be logged and returned in response body
     */
    protected void throwForbiddenException(final Logger logger, String message) {
        logger.error(message);
        throw new ForbiddenException(message);
    }

}
