package com.softserveinc.balance.calculator.api.resources.impl;

import javax.ws.rs.NotFoundException;

import com.softserveinc.balance.calculator.api.resources.RegisterResource;
import com.softserveinc.balance.calculator.dto.RegisterDTO;
import com.softserveinc.balance.calculator.service.RegisterService;

public class RegisterResourceImpl implements RegisterResource {

    private RegisterService registerService;
    
    public RegisterResourceImpl(RegisterService regsterService) {
        this.registerService = registerService;
    }
    
    public RegisterDTO getRegisterById(Long storeId, Long registerId) {
        RegisterDTO register = registerService.getRegisterById(storeId, registerId);
        if (register == null) {
            throw new NotFoundException();
        }
        return register;
    }

    public int save(RegisterDTO registerDto) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int update(RegisterDTO registerDto, Long storeId, Long registerId) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int delete(Long storeId, Long registerId) {
        // TODO Auto-generated method stub
        return 0;
    }

    
}
