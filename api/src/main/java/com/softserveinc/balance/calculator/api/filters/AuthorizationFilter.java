package com.softserveinc.balance.calculator.api.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthorizationFilter implements ContainerRequestFilter {

    private final String KEY = "GodSaveTheQuin";
    private final String DELIMITER = " ";
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authorization = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        System.out.println("Authorization: "  + getToken(authorization + " " + KEY));
    }
    
    private String getToken(String header) {
        return header.substring(header.indexOf(DELIMITER) + 1);
    }

}
