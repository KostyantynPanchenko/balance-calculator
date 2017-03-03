package com.softserveinc.balance.calculator.api.resources.impl;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.softserveinc.balance.calculator.api.resources.RegisterResource;
import com.softserveinc.balance.calculator.dto.RegisterDTO;
import com.softserveinc.balance.calculator.service.RegisterService;

public class RegisterResourceImpl implements RegisterResource {

    private RegisterService registerService;
    
    public RegisterResourceImpl(RegisterService registerService) {
        this.registerService = registerService;
    }
    
    public RegisterDTO getRegisterById(Long registerId) {
        RegisterDTO register = registerService.getRegisterById(registerId);
        checkOperationStatus(register == null ? 0 : 1, register.getStoreId(), registerId);
        return register;
    }

    public Response save(RegisterDTO registerDto, Long storeId) {
        System.out.println("RESOURCE");
        if (registerService.save(registerDto, storeId) != 1) {
            throw new InternalServerErrorException("Could not save entity");
        }
        return Response.status(Status.CREATED).build();

    }

    public Response update(RegisterDTO registerDto, Long storeId, Long registerId) {
        checkOperationStatus(registerService.update(registerDto, storeId, registerId), storeId, registerId);
        return Response.noContent().build();
    }

    public Response delete(Long storeId, Long registerId) {
        checkOperationStatus(registerService.delete(storeId, registerId), storeId, registerId);
        return Response.noContent().build();
    }

    private void checkOperationStatus(int status, Long storeId, Long registerId) {
        if (status != 1) {
            throw new NotFoundException("Register with id=" + registerId + " for specified store with id=" + storeId + " not found.");
        }
    }
}
