package com.softserveinc.balance.calculator.service.impl;

import com.softserveinc.balance.calculator.domain.Register;
import com.softserveinc.balance.calculator.dto.RegisterDTO;
import com.softserveinc.balance.calculator.repository.RegisterDAO;
import com.softserveinc.balance.calculator.service.RegisterService;

public class RegisterServiceImpl implements RegisterService {

    private RegisterDAO registerDao;
    
    public RegisterServiceImpl(RegisterDAO registerDao) {
        this.registerDao = registerDao;
    }
    
    public RegisterDTO getRegisterById(Long registerId) {
        Register register = registerDao.getRegisterById(registerId);
        return (register == null) ? null : new RegisterDTO(register);
    }

    public int save(RegisterDTO registerDto, Long storeId) {
        return registerDao.save(createRegister(registerDto, storeId));
    }

    public int update(RegisterDTO registerDto, Long storeId, Long registerId) {
        return registerDao.update(createRegister(registerDto, storeId), registerId);
    }

    public int delete(Long storeId, Long registerId) {
        return registerDao.delete(storeId, registerId);
    }
    
    private Register createRegister(RegisterDTO registerDto, Long storeId) {
        return new Register.Builder().setStoreId(storeId)
                                    .setName(registerDto.getName())
                                    .setTimezone(registerDto.getTimezone())
                                    .build();
    }

}
