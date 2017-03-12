package com.softserveinc.balance_calculator.api.resources.impl;

import java.net.URI;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserveinc.balance_calculator.api.resources.RegisterResource;
import com.softserveinc.balance_calculator.dto.RegisterDTO;
import com.softserveinc.balance_calculator.service.RegisterService;
import com.softserveinc.balance_calculator.service.exception.DataIntegrityViolationServiceException;
import com.softserveinc.balance_calculator.service.exception.EntityNotFoundServiceException;
import com.softserveinc.balance_calculator.service.exception.ServiceException;

import io.dropwizard.jersey.errors.ErrorMessage;

/**
 * Implementation of the <code>RegisterResource</code> interface.
 * 
 * @author  Kostyantyn Panchenko
 * @version 1.0
 * @see     RegisterResource
 * @since   03/05/2017
 */
public class RegisterResourceImpl implements RegisterResource {

    private final static Logger LOGGER = LoggerFactory.getLogger(RegisterResourceImpl.class);
    private RegisterService registerService;
    
    public RegisterResourceImpl(RegisterService registerService) {
        this.registerService = registerService;
    }
    
    @Override
    public Response getRegisterById(Long storeId, Long registerId) {
        LOGGER.info(String.format("Retrieving Register with id=%d for store No%d", registerId, storeId));
        RegisterDTO register;
        try {
            register = registerService.getRegisterById(storeId, registerId);
        } catch (EntityNotFoundServiceException notFound) {
            LOGGER.warn(notFound.getMessage(), notFound);
            return Response.status(Status.NOT_FOUND).entity(new ErrorMessage(404, buildMessage(registerId, storeId))).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ErrorMessage(500, e.getMessage())).build();
        }
        return Response.status(Status.OK).entity(register).build();
    }

    private String buildMessage(Long registerId, Long storeId) {
        return String.format("Register entity with id=%d for store with id=%d not found.", registerId, storeId);
    }

    @Override
    public Response create(RegisterDTO registerDto, Long storeId, UriInfo uriInfo) {
        LOGGER.info(String.format("Creating new Register for store No%d", storeId));
        registerDto.setStoreId(storeId);
        Long key;
        try {
            key = registerService.save(registerDto);
        } catch (DataIntegrityViolationServiceException violation) {
            LOGGER.warn(violation.getMessage(), violation);
            return Response.status(Status.CONFLICT).entity(new ErrorMessage(409, violation.getMessage())).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ErrorMessage(500, e.getMessage())).build();
        }
        registerDto.setId(key);
        LOGGER.info(String.format("Successfully created new Register with id=%d for store No%d", key, storeId));
        return Response.created(buildUri(uriInfo, key)).entity(registerDto).build();
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
    public Response update(RegisterDTO registerDto, Long storeId, Long registerId) {
        LOGGER.info(String.format("Updating Register with id=%d for store No%d.", registerId, storeId));
        registerDto.setStoreId(storeId);
        registerDto.setId(registerId);
        try {
            registerService.update(registerDto);
        } catch (DataIntegrityViolationServiceException violation) {
            LOGGER.warn(violation.getMessage(), violation);
            return Response.status(Status.CONFLICT).entity(new ErrorMessage(409, violation.getMessage())).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ErrorMessage(500, e.getMessage())).build();
        }
        registerDto.setId(registerId);
        registerDto.setStoreId(storeId);
        return Response.ok(registerDto).build();
    }

    @Override
    public Response delete(Long storeId, Long registerId) {
        LOGGER.info(String.format("Deleting Register with id=%d for store No%d", registerId, storeId));
        try {
            if (!oneRowModified(storeId, registerId)) {
                String message = String.format("Could not delete entity with id=%d.", registerId);
                LOGGER.error(message);
                return Response.status(Status.BAD_REQUEST).entity(new ErrorMessage(400, message + " Check if it exists.")).build();
            }
        } catch (DataIntegrityViolationServiceException violation) {
            LOGGER.warn(violation.getMessage(), violation);
            return Response.status(Status.CONFLICT).entity(new ErrorMessage(409, violation.getMessage())).build();
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ErrorMessage(500, e.getMessage())).build();
        }
        LOGGER.info(String.format("Register with id=%d for store No%d was successfully deleted.", registerId, storeId));
        return Response.noContent().build();
    }

    private boolean oneRowModified(Long storeId, Long registerId) throws ServiceException {
        return registerService.delete(storeId, registerId) == 1;
    }
    
}
