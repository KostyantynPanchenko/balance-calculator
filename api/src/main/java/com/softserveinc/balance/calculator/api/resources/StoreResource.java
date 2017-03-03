package com.softserveinc.balance.calculator.api.resources;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.softserveinc.balance.calculator.dto.StoreDTO;

@Path("/stores")
@Produces(MediaType.APPLICATION_JSON)
public interface StoreResource {

    @GET
    @Path("/{store-id}")
    StoreDTO getStoreById(@PathParam("store-id") Long id);
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Response save(@Valid StoreDTO storeDto);
    
    @PUT
    @Path("/{store-id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response update(StoreDTO storeDto, @PathParam("store-id") Long id);
    
    @DELETE
    @Path("/{store-id}")
    Response delete(@PathParam("store-id") Long id);
}
