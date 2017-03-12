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
        LOGGER.info(String.format("Retrieving store with id=%d.", id));
        try {
            return Response.status(Status.OK).entity(storeService.getStoreById(id)).build();
        } catch (EntityNotFoundServiceException notFound) {
            LOGGER.warn(notFound.getMessage(), notFound);
            return Response.status(Status.NOT_FOUND).entity(new ErrorMessage(404, buildMessage(id))).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ErrorMessage(500, e.getMessage())).build();
        }
    }
    
    private String buildMessage(Long registerId) {
        return String.format("Store with id=%d not found.", registerId);
    }

    @Override
    public Response create(StoreDTO storeDto, UriInfo uriInfo) {
        LOGGER.info("Creating new store record.");
        Long key;
        try {
            key = storeService.save(storeDto);
        } catch (DataIntegrityViolationServiceException violation) {
            LOGGER.error(violation.getMessage(), violation);
            return Response.status(Status.CONFLICT).entity(new ErrorMessage(409, violation.getMessage())).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ErrorMessage(500, e.getMessage())).build();
        }
        storeDto.setId(key);
        LOGGER.info(String.format("Successfully created new store with id=%d.", key));
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
    public Response update(StoreDTO storeDto, Long id) {
        LOGGER.info(String.format("Updating store with id=%d.", id));
        try {
            storeDto.setId(id);
            storeService.update(storeDto);
        } catch (DataIntegrityViolationServiceException violation) {
            LOGGER.error(violation.getMessage(), violation);
            return Response.status(Status.CONFLICT).entity(new ErrorMessage(409, violation.getMessage())).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ErrorMessage(500, e.getMessage())).build();
        }
        LOGGER.info(String.format("Successfully updated store with id=%d.", id));
        return Response.ok(storeDto).build();
    }

    @Override
    public Response delete(Long id) {
        LOGGER.info(String.format("Deleting store with id=%d.", id));
        try {
            if (!oneRowModified(id)) {
                String message = String.format("Could not delete store with id=%d", id);
                LOGGER.error(message);
                return Response.status(Status.BAD_REQUEST).entity(new ErrorMessage(400, message)).build();
            }
        } catch (DataIntegrityViolationServiceException violation) {
            LOGGER.error(violation.getMessage(), violation);
            return Response.status(Status.CONFLICT).entity(new ErrorMessage(409, violation.getMessage())).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ErrorMessage(500, e.getMessage())).build();
        }
        LOGGER.info(String.format("Succesfully deleted store with id=%d.", id));
        return Response.noContent().build();
    }

    private boolean oneRowModified(Long id) throws ServiceException {
        return storeService.delete(id) == 1;
    }
}
