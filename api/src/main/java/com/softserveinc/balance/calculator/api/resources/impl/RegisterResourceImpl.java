package com.softserveinc.balance.calculator.api.resources.impl;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
    
    public Response getRegisterById(Long registerId) {
        RegisterDTO register;
        try {
            register = registerService.getRegisterById(registerId);
        } catch (EntityNotFoundServiceException notFound) {
            return Response.status(Status.NOT_FOUND).entity(new ErrorMessage(404, buildMessage(registerId))).build();
        } catch (ServiceException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ErrorMessage(500, e.getMessage())).build();
        }
        return Response.status(Status.OK).entity(register).build();
    }

    private String buildMessage(Long registerId) {
        return String.format("Register entity with id=%d not found.", registerId);
    }

    public Response save(RegisterDTO registerDto, Long storeId) {
        try {
            registerService.save(registerDto, storeId);
        } catch (DataIntegrityViolationServiceException violation) {
            return Response.status(Status.CONFLICT).entity(new ErrorMessage(409, violation.getMessage())).build();
        } catch (ServiceException e) {
            return Response.status(Status.CONFLICT).entity(new ErrorMessage(500, e.getMessage())).build();
        }
        registerDto.setStoreId(storeId);
        return Response.status(Status.CREATED).entity(registerDto).build();

    }

    public Response update(RegisterDTO registerDto, Long storeId, Long registerId) {
        try {
            registerService.update(registerDto, storeId, registerId);
        } catch (DataIntegrityViolationServiceException violation) {
            return Response.status(Status.CONFLICT).entity(new ErrorMessage(409, violation.getMessage())).build();
        } catch (ServiceException e) {
            return Response.status(Status.CONFLICT).entity(new ErrorMessage(500, e.getMessage())).build();
        }
        registerDto.setId(registerId);
        registerDto.setStoreId(storeId);
        return Response.ok(registerDto).build();
    }

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
