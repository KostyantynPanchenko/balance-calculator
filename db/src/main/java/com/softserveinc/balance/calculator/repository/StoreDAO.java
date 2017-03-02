package com.softserveinc.balance.calculator.repository;

import com.softserveinc.balance.calculator.domain.Store;

public interface StoreDAO {
    
    Store getStoreById(Long id);
    
    int save(Store store);
    
    int update(Store store, Long id);
    
    int deleteById(long id);

}
