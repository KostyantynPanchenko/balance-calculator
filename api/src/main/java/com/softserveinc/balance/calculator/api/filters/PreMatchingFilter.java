
package com.softserveinc.balance.calculator.api.filters;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response.Status;

import com.softserveinc.balance.calculator.service.RegisterService;
import com.softserveinc.balance.calculator.service.StoreService;

/**
 * Authorization filter. Checks if request contain JWT and tenant can access
 * requested store.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 07/03/2017
 *
 */
@PreMatching
public class PreMatchingFilter extends AbstractFilter implements ContainerRequestFilter {

    public PreMatchingFilter(StoreService storeService, RegisterService registerService) {
        super(storeService, registerService);
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Long tenantId = getTenantIdFromJwt(requestContext);

        try {
            requestContext.setRequestUri(new URI(requestContext.getUriInfo().getAbsolutePath().toString() + "?tenantId=" + tenantId.toString()));
        } catch (URISyntaxException e) {
            throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
        }
    }

}
