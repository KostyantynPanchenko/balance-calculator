package com.softserveinc.balance.calculator.api.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.softserveinc.balance.calculator.dto.ConsumptionTransactionDTO;

/**
 * Exposes create endpoint for <code>ConsumptionTransaction</code> batch processing.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 06/03/2017
 */
@Path("/stores/{store_id}/register/{register_id}")
@Produces(MediaType.APPLICATION_JSON)
public interface ConsumptionTransactionResource {

    /**
     * This is a create endpoint for batch processing received <code>ConsumptionTransaction</code> entities.
     * 
     * @param consumptions a list of <code>ConsumptionTransactionDTO</code> entities
     * @return <code>Response</code> object
     */
    @POST
    @Path("/consumption")
    @Consumes(MediaType.APPLICATION_JSON)
    Response saveConsumptions(List<ConsumptionTransactionDTO> consumptions);
    
}
