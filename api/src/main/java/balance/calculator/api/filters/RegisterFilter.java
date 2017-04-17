package balance.calculator.api.filters;

import java.io.IOException;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import balance.calculator.api.filters.bindings.FilterRegisters;
import balance.calculator.dto.RegisterDTO;
import balance.calculator.service.RegisterService;
import balance.calculator.service.StoreService;

/**
 * Authorization filter. Checks if request is authorized to access requested <code>Register</code>.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 17/03/2017
 *
 */
@Provider
@FilterRegisters
public class RegisterFilter extends AbstractFilter implements ContainerRequestFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(RegisterFilter.class);
    private final String FORBIDDEN_STORE = "Unauthorized request to store id=%d by tenant id=%d";
    private final String FORBIDDEN_REGISTER = "Unauthorized request to register id=%d by tenant id=%d";
    private final String REGISTER_NOT_FOUND = "Register with id=%d not found in store No%d";
    
    public RegisterFilter(StoreService storeService, RegisterService registerService) {
        super(storeService, registerService);
    }
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Long tenantId = Long.valueOf(requestContext.getUriInfo().getQueryParameters().getFirst(TENANT_ID));
        Long pathStoreId = getStoreIdFromPath(requestContext);
        
        if (!isAuthorizedRequestToStore(getTenantId(pathStoreId), tenantId)) {
            throwForbiddenException(LOGGER, String.format(FORBIDDEN_STORE, new Object[] {pathStoreId, tenantId}));
        }
        
        if (!isPostRequest(requestContext)) {
            Long pathRegisterId = getRegisterIdFromPath(requestContext);
            if (!isAuthorizedRequestToRegister(pathStoreId, pathRegisterId)) {
                throwForbiddenException(LOGGER, String.format(FORBIDDEN_REGISTER, new Object[] {pathRegisterId, tenantId}));
            }
        }
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
            throw new NotFoundException(String.format(REGISTER_NOT_FOUND, registerId, storeId));
        }
        return register.getStoreId().equals(storeId);
    }

}
