package com.softserveinc.balance.calculator.api.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.softserveinc.balance.calculator.dto.ConsumptionTransactionDTO;

@Path("/stores/{store-id}/register/{register-id}")
@Produces(MediaType.APPLICATION_JSON)
public interface ConsumptionTransactionResource {

    @POST
    @Path("/consumption")
    @Consumes(MediaType.APPLICATION_JSON)
    Response saveConsumptions(List<ConsumptionTransactionDTO> consumptions);
    
}
