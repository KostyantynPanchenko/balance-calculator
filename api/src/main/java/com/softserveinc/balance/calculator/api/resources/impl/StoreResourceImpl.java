package com.softserveinc.balance.calculator.api.resources.impl;

import com.softserveinc.balance.calculator.api.resources.StoreResource;
import com.softserveinc.balance.calculator.dto.StoreDTO;
import com.softserveinc.balance.calculator.service.StoreService;

public class StoreResourceImpl implements StoreResource{

    private StoreService storeService;
    
    public StoreResourceImpl(StoreService storeService) {
        this.storeService = storeService;
    }
    public StoreDTO getStoreById(Long id) {
        return storeService.getStoreById(id);
    }

}
