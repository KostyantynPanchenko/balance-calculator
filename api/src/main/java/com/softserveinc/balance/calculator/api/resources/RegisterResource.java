package com.softserveinc.balance.calculator.api.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.softserveinc.balance.calculator.dto.RegisterDTO;

@Path("/stores/{store-id}/register")
@Produces(MediaType.APPLICATION_JSON)
public interface RegisterResource {

    @GET
    @Path("/{register-id}")
    Response getRegisterById(@PathParam("store-id") Long storeId, @PathParam("register-id") Long registerId);
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Response save(RegisterDTO registerDto, @PathParam("store-id") Long storeId, @Context UriInfo uriInfo);
    
    @PUT
    @Path("/{register-id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response update(RegisterDTO registerDto, @PathParam("store-id") Long storeId, @PathParam("register-id") Long registerId);
    
    @DELETE
    @Path("/{register-id}")
    Response delete(@PathParam("store-id") Long storeId, @PathParam("register-id") Long registerId);
}
