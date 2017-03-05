package com.softserveinc.balance.calculator.api.resources.impl;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.softserveinc.balance.calculator.api.exception.ErrorMessage;
import com.softserveinc.balance.calculator.api.resources.RegisterResource;
import com.softserveinc.balance.calculator.dto.RegisterDTO;
import com.softserveinc.balance.calculator.service.RegisterService;
import com.softserveinc.balance.calculator.service.exception.DataIntegrityViolationServiceException;
import com.softserveinc.balance.calculator.service.exception.EntityNotFoundServiceException;
import com.softserveinc.balance.calculator.service.exception.ServiceException;

public class RegisterResourceImpl implements RegisterResource {

    private RegisterService registerService;
    
    public RegisterResourceImpl(RegisterService registerService) {
        this.registerService = registerService;
    }
    
    @Override
    public Response getRegisterById(Long storeId, Long registerId) {
        RegisterDTO register;
        try {
            register = registerService.getRegisterById(storeId, registerId);
        } catch (EntityNotFoundServiceException notFound) {
            return Response.status(Status.NOT_FOUND).entity(new ErrorMessage(404, buildMessage(registerId, storeId))).build();
        } catch (ServiceException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ErrorMessage(500, e.getMessage())).build();
        }
        return Response.status(Status.OK).entity(register).build();
    }

    private String buildMessage(Long registerId, Long storeId) {
        return String.format("Register entity with id=%d for store with id=%d not found.", registerId, storeId);
    }

    @Override
    public Response save(RegisterDTO registerDto, Long storeId, UriInfo uriInfo) {
        registerDto.setStoreId(storeId);
        Long key;
        try {
            key = registerService.save(registerDto);
        } catch (DataIntegrityViolationServiceException violation) {
            return Response.status(Status.CONFLICT).entity(new ErrorMessage(409, violation.getMessage())).build();
        } catch (ServiceException e) {
            return Response.status(Status.CONFLICT).entity(new ErrorMessage(500, e.getMessage())).build();
        }
        registerDto.setId(key);
        return Response.status(Status.CREATED).entity(registerDto).build();

    }

    @Override
    public Response update(RegisterDTO registerDto, Long storeId, Long registerId) {
        registerDto.setStoreId(storeId);
        registerDto.setId(registerId);
        try {
            registerService.update(registerDto);
        } catch (DataIntegrityViolationServiceException violation) {
            return Response.status(Status.CONFLICT).entity(new ErrorMessage(409, violation.getMessage())).build();
        } catch (ServiceException e) {
            return Response.status(Status.CONFLICT).entity(new ErrorMessage(500, e.getMessage())).build();
        }
        registerDto.setId(registerId);
        registerDto.setStoreId(storeId);
        return Response.ok(registerDto).build();
    }

    @Override
    public Response delete(Long storeId, Long registerId) {
        try {
            registerService.delete(storeId, registerId);
        } catch (DataIntegrityViolationServiceException violation) {
            return Response.status(Status.CONFLICT).entity(new ErrorMessage(409, violation.getMessage())).build();
        } catch (ServiceException e) {
            return Response.status(Status.CONFLICT).entity(new ErrorMessage(500, e.getMessage())).build();
        }
        return Response.noContent().build();
    }
}
