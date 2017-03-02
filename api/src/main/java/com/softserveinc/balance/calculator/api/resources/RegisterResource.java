package com.softserveinc.balance.calculator.api.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.softserveinc.balance.calculator.dto.RegisterDTO;

@Path("/stores/{store-id}/register")
public interface RegisterResource {

    @GET
    @Path("/{register-id}")
    RegisterDTO getRegisterById(@PathParam("store-id") Long storeId, @PathParam("register-id") Long registerId);
    
    @POST
    int save(RegisterDTO registerDto);
    
    @PUT
    @Path("/register-id")
    int update(RegisterDTO registerDto, Long storeId, Long registerId);
    
    @DELETE
    @Path("/{register-id}")
    int delete(Long storeId, Long registerId);
}
