package com.softserveinc.balance.calculator.repository;

import com.softserveinc.balance.calculator.domain.Register;

public interface RegisterDAO {

    Register getRegisterById(Long storeId, Long registerId);
    
    int save(Register register);
    
    int update(Register register, Long storeId, Long registerId);
    
    int delete(Long storeId, Long registerId);
}
