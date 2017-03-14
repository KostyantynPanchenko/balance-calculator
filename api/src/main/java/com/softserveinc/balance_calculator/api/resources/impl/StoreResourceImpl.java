package com.softserveinc.balance_calculator.api.resources.impl;

import java.net.URI;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserveinc.balance_calculator.api.resources.StoreResource;
import com.softserveinc.balance_calculator.dto.StoreDTO;
import com.softserveinc.balance_calculator.service.StoreService;
import com.softserveinc.balance_calculator.service.exception.DataIntegrityViolationServiceException;
import com.softserveinc.balance_calculator.service.exception.EntityNotFoundServiceException;
import com.softserveinc.balance_calculator.service.exception.ServiceException;

import io.dropwizard.jersey.errors.ErrorMessage;

/**
 * Implementation of the <code>StoreResource</code> interface.
 * 
 * @author  Kostyantyn Panchenko
 * @version 1.0
 * @see     StoreResource
 * @since   03/05/2017
 */
public class StoreResourceImpl implements StoreResource {

    private final static Logger LOGGER = LoggerFactory.getLogger(StoreResourceImpl.class);
    private StoreService storeService;

    public StoreResourceImpl(StoreService storeService) {
        this.storeService = storeService;
    }

    @Override
    public Response getStoreById(Long id) {
        LOGGER.info("Retrieving store with id={}", id);
        try {
            return Response.status(Status.OK).entity(storeService.getStoreById(id)).build();
        } catch (EntityNotFoundServiceException notFound) {
            return getLoggedResponse(notFound, Status.NOT_FOUND, new ErrorMessage(404, buildMessage(id)));
        } catch (ServiceException e) {
            return getLoggedResponse(e, Status.INTERNAL_SERVER_ERROR, new ErrorMessage(500, e.getMessage()));
        }
    }
    
    private String buildMessage(Long registerId) {
        return String.format("Store with id=%d not found.", registerId);
    }

    @Override
    public Response create(StoreDTO storeDto, UriInfo uriInfo, Long tenantId) {
        storeDto.setTenantId(tenantId);
        Long key;
        try {
            key = storeService.save(storeDto);
        } catch (DataIntegrityViolationServiceException violation) {
            return getLoggedResponse(violation, Status.CONFLICT, new ErrorMessage(409, violation.getMessage()));
        } catch (ServiceException e) {
            return getLoggedResponse(e, Status.INTERNAL_SERVER_ERROR, new ErrorMessage(500, e.getMessage()));
        }
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

    @Override
    public Response update(StoreDTO storeDto, Long id, Long tenantId) {
        LOGGER.info("Updating store with id={}", id);
        storeDto.setId(id);
        storeDto.setTenantId(tenantId);
        try {
            if (!oneRowUpdated(storeDto)) {
                return getLoggedResponse(Status.BAD_REQUEST, String.format("Could not update entity with id=%d", storeDto));
            }
        } catch (DataIntegrityViolationServiceException violation) {
            return getLoggedResponse(violation, Status.CONFLICT, new ErrorMessage(409, violation.getMessage()));
        } catch (ServiceException e) {
            return getLoggedResponse(e, Status.INTERNAL_SERVER_ERROR, new ErrorMessage(500, e.getMessage()));
        }
        LOGGER.info("Successfully updated store with id={}", id);
        return Response.ok(storeDto).build();
    }

    private boolean oneRowUpdated(StoreDTO storeDto) throws DataIntegrityViolationServiceException, ServiceException {
        return storeService.update(storeDto) == 1;
    }

    @Override
    public Response delete(Long id) {
        LOGGER.info("Deleting store with id={}", id);
        try {
            if (!oneRowModified(id)) {
                return getLoggedResponse(Status.BAD_REQUEST, String.format("Could not delete store with id=%d", id));
            }
        } catch (DataIntegrityViolationServiceException violation) {
            return getLoggedResponse(violation, Status.CONFLICT, new ErrorMessage(409, violation.getMessage()));
        } catch (ServiceException e) {
            return getLoggedResponse(e, Status.INTERNAL_SERVER_ERROR, new ErrorMessage(500, e.getMessage()));
        }
        LOGGER.info("Succesfully deleted store with id={}", id);
        return Response.noContent().build();
    }

    private boolean oneRowModified(Long id) throws ServiceException {
        return storeService.delete(id) == 1;
    }
    
    private Response getLoggedResponse(Exception e, Status status, ErrorMessage message) {
        LOGGER.error(e.getMessage(), e);
        return Response.status(status).entity(message).build();
    }

    private Response getLoggedResponse(Status status, String message) {
        LOGGER.error(message);
        return Response.status(status).entity(new ErrorMessage(404, message)).build();
    }
}
