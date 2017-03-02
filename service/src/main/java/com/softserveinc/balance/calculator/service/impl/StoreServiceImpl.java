package com.softserveinc.balance.calculator.service.impl;

import com.softserveinc.balance.calculator.domain.Store;
import com.softserveinc.balance.calculator.dto.StoreDTO;
import com.softserveinc.balance.calculator.repository.StoreDAO;
import com.softserveinc.balance.calculator.service.StoreService;

public class StoreServiceImpl implements StoreService {

    private StoreDAO storeDao;
    
    public StoreServiceImpl(StoreDAO storeDao){
        this.storeDao = storeDao;
    }
    
    public StoreDTO getStoreById(Long id) {
        StoreDTO storeDto = null;
        if (storeDao.getStoreById(id) != null) {
            storeDto = new StoreDTO(storeDao.getStoreById(id));
        }
        return storeDto;
    }

    public int update(StoreDTO storeDto, Long id) {
        Store store = new Store.Builder()
                            .setId(id)
                            .setTenantId(storeDto.getTenantId())
                            .setName(storeDto.getName())
                            .setDescription(storeDto.getDescription())
                            .build();
        return storeDao.update(store, id);
    }

    public int delete(Long id) {
        return storeDao.deleteById(id);
    }

    public int save(StoreDTO storeDto) {
        Store store = new Store.Builder()
                            .setTenantId(storeDto.getTenantId())
                            .setName(storeDto.getName())
                            .setDescription(storeDto.getDescription())
                            .build();
        return storeDao.save(store);
    }

}
