package com.softserveinc.balance.calculator.repository;

import com.softserveinc.balance.calculator.domain.Register;
import com.softserveinc.balance.calculator.repository.exception.RepositoryException;

public interface RegisterDAO {

    Register getRegisterById(Long storeId, Long registerId) throws RepositoryException;
    
    Long save(Register register) throws RepositoryException;
    
    int update(Register register) throws RepositoryException;
    
    int delete(Long registerId, Long registerId2) throws RepositoryException;
}
