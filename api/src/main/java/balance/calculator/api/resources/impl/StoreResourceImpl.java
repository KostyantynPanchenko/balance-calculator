package balance.calculator.api.resources.impl;

import java.net.URI;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import balance.calculator.api.filters.bindings.FilterStores;
import balance.calculator.api.resources.StoreResource;
import balance.calculator.dto.StoreDTO;
import balance.calculator.service.StoreService;

/**
 * Implementation of the <code>StoreResource</code> interface.
 * 
 * @author  Kostyantyn Panchenko
 * @version 1.0
 * @see     StoreResource
 * @since   03/05/2017
 */
@Component
public class StoreResourceImpl implements StoreResource {

    private final static Logger LOGGER = LoggerFactory.getLogger(StoreResourceImpl.class);
    private StoreService storeService;

    public StoreResourceImpl(StoreService storeService) {
        this.storeService = storeService;
    }

    @FilterStores
    @Override
    public Response getStoreById(Long id) {
        LOGGER.info("Retrieving store with id={}", id);
        StoreDTO store  = storeService.getStoreById(id);
        if (store == null) {
            return getLoggedResponse(Status.NOT_FOUND, String.format("Store with id=%d not found.", id));
        }
        return Response.status(Status.OK).entity(store).build();
    }

    @FilterStores
    @Override
    public Response create(StoreDTO storeDto, UriInfo uriInfo, Long tenantId) {
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

    @FilterStores
    @Override
    public Response update(StoreDTO storeDto, Long id, Long tenantId) {
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

    @FilterStores
    @Override
    public Response delete(Long id) {
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
