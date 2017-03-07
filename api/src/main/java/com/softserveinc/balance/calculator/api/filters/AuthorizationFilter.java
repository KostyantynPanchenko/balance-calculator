package com.softserveinc.balance.calculator.api.filters;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.eclipse.jetty.http.HttpMethod;
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
    
    public AuthorizationFilter(StoreService storeService) {
        this.storeService = storeService;
    }
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (!isPostMethod(requestContext)) {
            Long jwtTenantId = getTenantIdFromJwt(getJwt(requestContext));
            Long pathStoreId = getStoreIdFromPath(requestContext);
            
            try {
                if (!isAuthorized(getTenantId(pathStoreId), jwtTenantId)) {
                    LOGGER.error(String.format(UNAUTHORIZED, pathStoreId, jwtTenantId), pathStoreId, jwtTenantId);
                    requestContext.abortWith(Response.status(Status.UNAUTHORIZED).entity(new ErrorMessage(401, String.format(UNAUTHORIZED, pathStoreId, jwtTenantId))).build());
                }
            } catch (EntityNotFoundServiceException notFound) {
                LOGGER.error(String.format(NOT_FOUND, pathStoreId));
                requestContext.abortWith(Response.status(Status.NOT_FOUND).entity(new ErrorMessage(404, String.format(NOT_FOUND, pathStoreId))).build());
            } catch (ServiceException e) {
                LOGGER.error(String.format(SERVER_ERROR, pathStoreId), e.getMessage());
            }
        }
        
    }

    private Long getTenantId(Long pathStoreId) throws ServiceException {
        return storeService.getStoreById(pathStoreId).getTenantId();
    }

    private boolean isAuthorized(Long tenantId, Long tenantId2) {
        return tenantId.equals(tenantId2);
    }
    
    private boolean isPostMethod(ContainerRequestContext requestContext) {
        return requestContext.getMethod().equals(HttpMethod.POST);
    }

    private Long getStoreIdFromPath(ContainerRequestContext requestContext) {
        return Long.valueOf(requestContext.getUriInfo().getPathSegments().get(STORE_ID_PATH_POSITION).toString());
    }

    private String getJwt(ContainerRequestContext requestContext) {
        String header = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        return header.substring(header.indexOf(DELIMITER) + 1);
    }
    
    private Long getTenantIdFromJwt(String jwt) {
        try {
            return Long.valueOf(getTenantIdClaim(jwt));
        } catch (ExpiredJwtException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedJwtException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MalformedJwtException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SignatureException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            LOGGER.error("Could not parse tenantId claim.", e);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    private String getTenantIdClaim(String jwt) throws UnsupportedEncodingException {
        return (String) Jwts.parser()
                .setSigningKey(KEY.getBytes(CHARSET_NAME))
                .parseClaimsJws(jwt)
                .getBody()
                .get(TENANT_ID);
    }

}
