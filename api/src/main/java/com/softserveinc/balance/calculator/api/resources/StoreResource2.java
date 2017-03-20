package com.softserveinc.balance.calculator.api.resources;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserveinc.balance.calculator.api.resources.impl.StoreResourceImpl;
import com.softserveinc.balance.calculator.dto.StoreDTO;
import com.softserveinc.balance.calculator.service.StoreService;

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
public class StoreResource2 {

    private final static Logger LOGGER = LoggerFactory.getLogger(StoreResourceImpl.class);
    private StoreService storeService;

    public StoreResource2(StoreService storeService) {
        this.storeService = storeService;
    }
    
    /**
     * Retrieves a <code>Store</code> entity with given id.
     * 
     * @param id    <code>Store</code> entity id
     * @return      <code>Response</code> object which holds
     *              response status code and representation
     *              of <code>Store</code> entity
     */
    @GET
    @Path("/{store_id}")
    Response getStoreById(@PathParam("store_id") Long id) {
        LOGGER.info("Retrieving store with id={}", id);
        StoreDTO store  = storeService.getStoreById(id);
        if (store == null) {
            return getLoggedResponse(Status.NOT_FOUND, String.format("Store with id=%d not found.", id));
        }
        return Response.status(Status.OK).entity(store).build();
    }
    
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
    Response create(@Valid @NotNull StoreDTO storeDto, @Context UriInfo uriInfo, @QueryParam("tenantId") Long tenantId) {
        storeDto.setTenantId(tenantId);
        Long key = storeService.save(storeDto);
        storeDto.setId(key);
        LOGGER.info("Successfully created new store with id={}", key);
        return Response.created(buildUri(uriInfo, key)).entity(storeDto).build();
    }

    /**
     * Constructs URI to created resource.
     * 
     * @param uriInfo <code>UriInfo</code> object
     * @param key auto generated key of newly created record
     * @return created URI
     */
    private URI buildUri(UriInfo uriInfo, Long key) {
        return uriInfo.getAbsolutePathBuilder().path(key.toString()).build();
    }
    
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
    @Path("/{store_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response update(@Valid @NotNull StoreDTO storeDto, @PathParam("store_id") Long id, @QueryParam("tenantId") Long tenantId) {
        LOGGER.info("Updating store with id={}", id);
        storeDto.setId(id);
        storeDto.setTenantId(tenantId);
        if (!oneRowUpdated(storeDto)) {
            return getLoggedResponse(Status.BAD_REQUEST, String.format("Could not update entity with id=%d", storeDto));
        }
        LOGGER.info("Successfully updated store with id={}", id);
        return Response.ok(storeDto).build();
    }

    private boolean oneRowUpdated(StoreDTO storeDto) {
        return storeService.update(storeDto) == 1;
    }
    
    
    /**
     * Deletes <code>Store</code> entity with specified id.
     * 
     * @param id        id of <code>Store</code> entity to be deleted
     * @return          <code>Response</code> object containing 
     *                  response status code
     */
    @DELETE
    @Path("/{store_id}")
    Response delete(@PathParam("store_id") Long id) {
        LOGGER.info("Deleting store with id={}", id);
        if (!oneRowdeleted(id)) {
            return getLoggedResponse(Status.BAD_REQUEST, String.format("Could not delete store with id=%d", id));
        }
        LOGGER.info("Succesfully deleted store with id={}", id);
        return Response.noContent().build();
    }

    private boolean oneRowdeleted(Long id) {
        return storeService.delete(id) == 1;
    }
    
    private Response getLoggedResponse(Status status, String message) {
        LOGGER.error(message);
        return Response.status(status).entity(message).build();
    }
    
}
