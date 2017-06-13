package balance.calculator.api.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

/**
 * CORS filter.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 13/06/2017
 *
 */
@Provider
public class CrossOriginFilter implements ContainerResponseFilter {

    private static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    private static final String ALL = "*";
    private static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
    private static final String TRUE = "true";
    private static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
    private static final String HEADERS = "origin, content-type, accept, authorization";
    private static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
    private static final String METHODS = "GET, POST, PUT, DELETE, OPTIONS, HEAD";

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        MultivaluedMap<String, Object> headers = responseContext.getHeaders();
        headers.add(ACCESS_CONTROL_ALLOW_ORIGIN, ALL);
        headers.add(ACCESS_CONTROL_ALLOW_CREDENTIALS, TRUE);
        headers.add(ACCESS_CONTROL_ALLOW_HEADERS, HEADERS);
        headers.add(ACCESS_CONTROL_ALLOW_METHODS, METHODS);
    }

}
