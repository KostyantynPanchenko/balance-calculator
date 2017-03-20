package com.softserveinc.balance.calculator.api.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserveinc.balance.calculator.api.filters.bindings.FilterStores;
import com.softserveinc.balance.calculator.service.RegisterService;
import com.softserveinc.balance.calculator.service.StoreService;

/**
 * Authorization filter. Checks if request contain JWT and tenant can access requested store.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 07/03/2017
 *
 */
@Provider
@FilterStores
public class StoreFilter extends AbstractFilter implements ContainerRequestFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(StoreFilter.class);
    private final String FORBIDDEN = "Unauthorized request to store id=%d by tenantId=%d";
    
    public StoreFilter(StoreService storeService, RegisterService registerService) {
        super(storeService, registerService);
    }
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        System.out.println("STORE FILTER");
        
        Long tenantId = Long.valueOf(requestContext.getUriInfo().getQueryParameters().getFirst(TENANT_ID));
        
        if (!isPostRequest(requestContext)) {
            Long pathStoreId = getStoreIdFromPath(requestContext);
            
            if (!isAuthorizedRequestToStore(getTenantId(pathStoreId), tenantId)) {
                throwForbiddenException(LOGGER, String.format(FORBIDDEN, new Object[] {pathStoreId, tenantId}));
            }
        }
    }

}
