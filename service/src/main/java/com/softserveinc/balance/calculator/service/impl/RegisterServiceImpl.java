package com.softserveinc.balance.calculator.service.impl;

import com.softserveinc.balance.calculator.domain.Register;
import com.softserveinc.balance.calculator.dto.RegisterDTO;
import com.softserveinc.balance.calculator.repository.RegisterDAO;
import com.softserveinc.balance.calculator.service.RegisterService;
import com.softserveinc.balance.calculator.service.impl.mappers.RegisterMapper;

public class RegisterServiceImpl implements RegisterService {

    private RegisterDAO registerDao;
    
    public RegisterServiceImpl(RegisterDAO registerDao) {
        this.registerDao = registerDao;
    }
    
    public RegisterDTO getRegisterById(Long registerId) {
            Register register = registerDao.getRegisterById(registerId);
            return (register == null) ? null : RegisterMapper.toDTO(register);
    }

    public Long save(RegisterDTO registerDto) {
        return registerDao.insert(RegisterMapper.toDomainObject(registerDto));
    }

    public int update(RegisterDTO registerDto) {
        return registerDao.update(RegisterMapper.toDomainObject(registerDto));
    }

    public int delete(Long registerId) {
        return registerDao.delete(registerId);
    }

}
