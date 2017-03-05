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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.softserveinc.balance.calculator.dto.StoreDTO;

/**
 * A collection of CRUD endpoints for <code>Store</code> entity.
 * The user of this interface has precise control over the 
 * <code>Store</code> entities. The user can access entities by 
 * their id and perform reading, creation, updating and deletion.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 03/05/2017
 */
@Path("/stores")
@Produces(MediaType.APPLICATION_JSON)
public interface StoreResource {

    /**
     * Retrieves a <code>Store</code> entity with given id.
     * 
     * @param id    <code>Store</code> entity id
     * @return      <code>Response</code> object which holds
     *              response status code and representation
     *              of <code>Store</code> entity
     */
    @GET
    @Path("/{store-id}")
    Response getStoreById(@PathParam("store-id") Long id);
    
    /**
     * Creates a new <code>Store</code> entity with fields values 
     * provided in request payload.
     * 
     * @param storeDto  <code>Store</code> data transfer object to
     *                  which request payload data will be mapped to
     * @param uriInfo   an object that provides access to application 
     *                  and request URI information
     * @return          <code>Response</code> object which holds 
     *                  representation of saved <code>Store</code> entity,
     *                  response status code and URI of newly created entity
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Response save(@Valid StoreDTO storeDto, @Context UriInfo uriInfo);
    
    /**
     * Updates <code>Store</code> entity with values provided in request payload.
     * 
     * @param storeDto  <code>Store</code> data transfer object to
     *                  which request payload data will be mapped to
     * @param id        id of <code>Store</code> entity to be updated
     * @return          <code>Response</code> object containing 
     *                  representation of updated <code>Store</code> 
     *                  entity and response status code
     */
    @PUT
    @Path("/{store-id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response update(StoreDTO storeDto, @PathParam("store-id") Long id);
    
    
    /**
     * Deletes <code>Store</code> entity with specified id.
     * 
     * @param id        id of <code>Store</code> entity to be deleted
     * @return          <code>Response</code> object containing 
     *                  response status code
     */
    @DELETE
    @Path("/{store-id}")
    Response delete(@PathParam("store-id") Long id);
    
}
