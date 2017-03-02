package com.softserveinc.balance.calculator.repository;

import com.softserveinc.balance.calculator.domain.Store;

public interface StoreDAO {
    
    void save(Store store);
    
    Store getStoreById(Long id);
    
    void update(Store store);
    
    void deleteById(long id);

}
