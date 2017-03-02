package com.softserveinc.balance.calculator.api.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.softserveinc.balance.calculator.dto.StoreDTO;


@Path("/store")
public interface StoreResource {

    @GET
    @Path("/{id}")
    StoreDTO getStoreById(@PathParam("id") Long id);
}
