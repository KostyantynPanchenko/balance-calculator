package com.softserveinc.balance.calculator.api.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/stores/{store-id}/register/{register-id}/balance")
@Produces(MediaType.APPLICATION_JSON)
public interface BalanceResource {

    @GET
    Response getBalance(@QueryParam("date") String date);
    
}
