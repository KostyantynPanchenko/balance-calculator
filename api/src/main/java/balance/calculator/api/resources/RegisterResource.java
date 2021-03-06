package balance.calculator.api.resources;

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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import balance.calculator.dto.RegisterDTO;

/**
 * A collection of CRUD endpoints for <code>Register</code> entity. The user of
 * this interface has precise control over the <code>Register</code> entities.
 * The user can access entities by their id and perform reading, creation,
 * updating and deletion.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 03/05/2017
 */
@Produces(MediaType.APPLICATION_JSON)
@Path("/stores/{store_id}/register")
public interface RegisterResource {

    /**
     * GET endpoint for <code>Register</code> entity with given id.
     * 
     * @param registerId    <code>Register</code> entity id
     * @return              <code>Response</code> object which holds response status 
     *                      code and representation of <code>Register</code> entity
     */
    @GET
    @Path("/{register_id}")
    Response getRegisterById(@PathParam("register_id") Long registerId);

    /**
     * Creates endpoint for <code>Register</code> entity.
     * 
     * @param registerDto   <code>Register</code> data transfer object to which request
     *                      payload will be mapped to
     * @param storeId       <code>Store</code> id to which <code>Register</code> entity
     *                      relates to
     * @param uriInfo       an object that provides access to application and request URI
     *                      information
     * @return              <code>Response</code> object which holds representation of saved
     *                      <code>Register</code> entity, response status code and URI of
     *                      newly created entity
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Response create(@Valid @NotNull RegisterDTO registerDto, @PathParam("store_id") Long storeId, @Context UriInfo uriInfo);

    /**
     * Update endpoint for <code>Register</code> entity.
     * 
     * @param registerDto   <code>Register</code> data transfer object to which request
     *                      payload data will be mapped to
     * @param registerId    id of <code>Register</code> entity to be updated
     * @return              <code>Response</code> object containing representation of 
     *                      updated <code>Store</code> entity and response status code
     */
    @PUT
    @Path("/{register_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response update(@Valid @NotNull RegisterDTO registerDto, @PathParam("store_id") Long storeId, @PathParam("register_id") Long registerId);

    /**
     * Delete endpoint for  <code>Register</code> entity.
     * 
     * @param registerId    id of <code>Register</code> entity to be deleted
     * @return              <code>Response</code> object containing response status code
     */
    @DELETE
    @Path("/{register_id}")
    Response delete(@PathParam("register_id") Long registerId);

}
