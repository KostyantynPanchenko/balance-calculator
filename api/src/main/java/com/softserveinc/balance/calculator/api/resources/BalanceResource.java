package com.softserveinc.balance.calculator.api.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Endpoint for retrieving balance.
 * 
 * @author Kostynatyn Panchenko
 * @version 1.0
 * @since 03/09/2017
 *
 */
@Path("/stores/{store_id}/register/{register_id}/balance")
@Produces(MediaType.APPLICATION_JSON)
public interface BalanceResource {

    /**
     * Retrieves balance for specified date. If no date specified - the
     * last one calculated balance will be returned.
     * 
     * @param date date of balance to be retrieved
     * @return balance for specified date
     */
    @GET
    Response getBalance(@PathParam("register_id") Long registerId, @QueryParam("date") String date);
    
}
