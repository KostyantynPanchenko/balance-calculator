package com.softserveinc.balance.calculator.service.impl;

import com.softserveinc.balance.calculator.dto.StoreDTO;
import com.softserveinc.balance.calculator.repository.StoreDAO;
import com.softserveinc.balance.calculator.service.StoreService;

public class StoreServiceImpl implements StoreService {

    private StoreDAO storeDao;
    
    public StoreServiceImpl(StoreDAO storeDao){
        this.storeDao = storeDao;
    }
    
    public StoreDTO getStoreById(Long id) {        
        return new StoreDTO(storeDao.getStoreById(id));
    }

}
