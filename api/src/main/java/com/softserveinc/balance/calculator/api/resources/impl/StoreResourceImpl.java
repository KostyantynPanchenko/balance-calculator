package com.softserveinc.balance.calculator.api.resources.impl;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.softserveinc.balance.calculator.api.exception.ErrorMessage;
import com.softserveinc.balance.calculator.api.resources.StoreResource;
import com.softserveinc.balance.calculator.dto.StoreDTO;
import com.softserveinc.balance.calculator.service.StoreService;
import com.softserveinc.balance.calculator.service.exception.DataIntegrityViolationServiceException;
import com.softserveinc.balance.calculator.service.exception.EntityNotFoundServiceException;
import com.softserveinc.balance.calculator.service.exception.ServiceException;

public class StoreResourceImpl implements StoreResource {

    private StoreService storeService;

    public StoreResourceImpl(StoreService storeService) {
        this.storeService = storeService;
    }

    public Response getStoreById(Long id) {
        try {
            return Response.status(Status.OK).entity(storeService.getStoreById(id)).build();
        } catch (EntityNotFoundServiceException notFound) {
            return Response.status(Status.NOT_FOUND).entity(new ErrorMessage(404, buildMessage(id))).build();
        } catch (ServiceException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ErrorMessage(500, e.getMessage())).build();
        }
    }
    
    private String buildMessage(Long registerId) {
        return String.format("Register entity with id=%d not found.", registerId);
    }

    public Response save(StoreDTO storeDto) {
        try {
            storeService.save(storeDto);
        } catch (DataIntegrityViolationServiceException violation) {
            return Response.status(Status.CONFLICT).entity(new ErrorMessage(409, violation.getMessage())).build();
        } catch (ServiceException e) {
            return Response.status(Status.CONFLICT).entity(new ErrorMessage(500, e.getMessage())).build();
        }
        return Response.status(Status.CREATED).entity(storeDto).build();
    }

    public Response update(StoreDTO storeDto, Long id) {
        try {
            storeDto.setId(id);
            storeService.update(storeDto);
        } catch (DataIntegrityViolationServiceException violation) {
            return Response.status(Status.CONFLICT).entity(new ErrorMessage(409, violation.getMessage())).build();
        } catch (ServiceException e) {
            return Response.status(Status.CONFLICT).entity(new ErrorMessage(500, e.getMessage())).build();
        }
        return Response.ok(storeDto).build();
    }

    public Response delete(Long id) {
        try {
            storeService.delete(id);
        } catch (DataIntegrityViolationServiceException violation) {
            return Response.status(Status.CONFLICT).entity(new ErrorMessage(409, violation.getMessage())).build();
        } catch (ServiceException e) {
            return Response.status(Status.CONFLICT).entity(new ErrorMessage(500, e.getMessage())).build();
        }
        return Response.noContent().build();
    }
}
