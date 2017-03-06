package com.softserveinc.balance.calculator.api.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.softserveinc.balance.calculator.dto.ContributionTransactionDTO;

@Path("/stores/{store-id}/register/{register-id}")
@Produces(MediaType.APPLICATION_JSON)
public interface ContributionTransactionResource {
    
    @POST
    @Path("/contribution")
    @Consumes(MediaType.APPLICATION_JSON)
    Response saveContributions(List<ContributionTransactionDTO> contributions);
    
}
