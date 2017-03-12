package com.softserveinc.balance_calculator.api.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.validator.constraints.NotEmpty;

import com.softserveinc.balance_calculator.dto.ConsumptionTransactionDTO;

/**
 * Create endpoint for <code>ConsumptionTransaction</code> batch processing.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 06/03/2017
 */
@Path("/stores/{store_id}/register/{register_id}/consumption")
@Produces(MediaType.APPLICATION_JSON)
public interface ConsumptionTransactionResource {

    /**
     * TPerforms batch processing of received <code>ConsumptionTransaction</code> entities.
     * 
     * @param consumptions a list of <code>ConsumptionTransactionDTO</code> entities
     * @param registerId - id of <code>Register</code>
     * @return <code>Response</code> object
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Response saveConsumptions(@NotEmpty List<ConsumptionTransactionDTO> consumptions, @PathParam("register_id") Long registerId);
    
}
