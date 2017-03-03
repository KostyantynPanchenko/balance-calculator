package com.softserveinc.balance.calculator.repository;

import com.softserveinc.balance.calculator.domain.Register;
import com.softserveinc.balance.calculator.repository.exception.RepositoryException;

public interface RegisterDAO {

    Register getRegisterById(Long storeId) throws RepositoryException;
    
    int save(Register register) throws RepositoryException;
    
    int update(Register register, Long storeId) throws RepositoryException;
    
    int delete(Long registerId, Long registerId2) throws RepositoryException;
}
