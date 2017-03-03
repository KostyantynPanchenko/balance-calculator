package com.softserveinc.balance.calculator.repository;

import com.softserveinc.balance.calculator.domain.Register;

public interface RegisterDAO {

    Register getRegisterById(Long storeId);
    
    int save(Register register);
    
    int update(Register register, Long storeId);
    
    int delete(Long registerId, Long registerId2);
}
