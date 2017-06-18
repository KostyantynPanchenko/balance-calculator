package balance.calculator.api.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import balance.calculator.api.filters.bindings.FilterStores;
import balance.calculator.service.RegisterService;
import balance.calculator.service.StoreService;

/**
 * Authorization filter. Checks if request is authorized to access requested <code>Store</code>.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 07/03/2017
 *
 */
@Component
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
        Long tenantId = Long.valueOf(requestContext.getUriInfo().getQueryParameters().getFirst(TENANT_ID));
        
        if (!isPostRequest(requestContext)) {
            Long pathStoreId = getStoreIdFromPath(requestContext);
            
            if (!isAuthorizedRequestToStore(getTenantId(pathStoreId), tenantId)) {
                throwForbiddenException(LOGGER, String.format(FORBIDDEN, new Object[] {pathStoreId, tenantId}));
            }
        }
    }

}
