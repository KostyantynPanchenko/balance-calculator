package com.softserveinc.balance.calculator.repository;

import com.softserveinc.balance.calculator.domain.Store;
import com.softserveinc.balance.calculator.repository.exception.RepositoryException;

public interface StoreDAO {
    
    Store getStoreById(Long id) throws RepositoryException;
    
    Long save(Store store) throws RepositoryException;
    
    int update(Store store) throws RepositoryException;
    
    int deleteById(Long id) throws RepositoryException;

}
