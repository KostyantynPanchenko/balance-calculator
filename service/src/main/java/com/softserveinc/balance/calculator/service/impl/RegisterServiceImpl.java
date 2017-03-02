package com.softserveinc.balance.calculator.service.impl;

import com.softserveinc.balance.calculator.domain.Register;
import com.softserveinc.balance.calculator.dto.RegisterDTO;
import com.softserveinc.balance.calculator.repository.RegisterDAO;
import com.softserveinc.balance.calculator.service.RegisterService;

public class RegisterServiceImpl implements RegisterService {

    private RegisterDAO registerDao;
    
    public RegisterServiceImpl(RegisterDAO dao) {
        this.registerDao = dao;
    }
    
    public RegisterDTO getRegisterById(Long storeId, Long registerId) {
        Register register = registerDao.getRegisterById(storeId, registerId);
        return (register == null) ? null : new RegisterDTO(register);
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
