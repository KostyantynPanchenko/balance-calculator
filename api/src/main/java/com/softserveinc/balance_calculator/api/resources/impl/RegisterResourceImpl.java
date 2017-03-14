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
    public Response getRegisterById(Long registerId) {
        LOGGER.info("Retrieving Register with id={}", registerId);
        RegisterDTO register;
        try {
            register = registerService.getRegisterById(registerId);
        } catch (EntityNotFoundServiceException notFound) {
            return getLoggedResponse(notFound, Status.BAD_REQUEST, new ErrorMessage(404, buildMessage(registerId)));
        } catch (ServiceException e) {
            return getLoggedResponse(e, Status.INTERNAL_SERVER_ERROR, new ErrorMessage(500, e.getMessage()));
        }
        return Response.status(Status.OK).entity(register).build();
    }

    private String buildMessage(Long registerId) {
        return String.format("Register entity with id=%d not found", registerId);
    }

    @Override
    public Response create(RegisterDTO registerDto, Long storeId, UriInfo uriInfo) {
        LOGGER.info("Creating new Register for Store id={}", storeId);
        registerDto.setStoreId(storeId);
        Long key;
        try {
            key = registerService.save(registerDto);
        } catch (DataIntegrityViolationServiceException violation) {
            return getLoggedResponse(violation, Status.CONFLICT, new ErrorMessage(409, violation.getMessage()));
        } catch (ServiceException e) {
            return getLoggedResponse(e, Status.INTERNAL_SERVER_ERROR, new ErrorMessage(500, e.getMessage()));
        }
        registerDto.setId(key);
        LOGGER.info("Successfully created new Register with id={} for Store id={}", key, storeId);
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
        LOGGER.info("Updating Register with id={} for Store id={}", registerId, storeId);
        registerDto.setStoreId(storeId);
        registerDto.setId(registerId);
        
        System.out.println(registerDto);
        
        try {
            if (!oneRowUpdated(registerDto)) {
                return getLoggedResponse(Status.BAD_REQUEST, String.format("Could not update Register with id=%d", registerId));
            }
        } catch (DataIntegrityViolationServiceException violation) {
            return getLoggedResponse(violation, Status.CONFLICT, new ErrorMessage(409, violation.getMessage()));
        } catch (ServiceException e) {
            return getLoggedResponse(e, Status.INTERNAL_SERVER_ERROR, new ErrorMessage(500, e.getMessage()));
        }
        registerDto.setId(registerId);
        registerDto.setStoreId(storeId);
        return Response.ok(registerDto).build();
    }

    private boolean oneRowUpdated(RegisterDTO registerDto) throws DataIntegrityViolationServiceException, ServiceException {
        return registerService.update(registerDto) == 1;
    }

    @Override
    public Response delete(Long registerId) {
        LOGGER.info("Deleting Register with id={}", registerId);
        try {
            if (!oneRowDeleted(registerId)) {
                return getLoggedResponse(Status.NOT_FOUND, String.format("Could not delete Register with id=%d", registerId));
            }
        } catch (DataIntegrityViolationServiceException violation) {
            return getLoggedResponse(violation, Status.CONFLICT, new ErrorMessage(409, violation.getMessage()));
        } catch (ServiceException e) {
            return getLoggedResponse(e, Status.INTERNAL_SERVER_ERROR, new ErrorMessage(500, e.getMessage()));
        }
        LOGGER.info("Register with id={} was successfully deleted", registerId);
        return Response.noContent().build();
    }

    private boolean oneRowDeleted(Long registerId) throws ServiceException {
        return registerService.delete(registerId) == 1;
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
