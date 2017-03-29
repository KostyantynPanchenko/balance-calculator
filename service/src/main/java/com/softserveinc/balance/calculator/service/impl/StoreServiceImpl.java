package com.softserveinc.balance.calculator.service.impl;

import com.softserveinc.balance.calculator.domain.Store;
import com.softserveinc.balance.calculator.dto.StoreDTO;
import com.softserveinc.balance.calculator.repository.StoreDAO;
import com.softserveinc.balance.calculator.service.StoreService;
import com.softserveinc.balance.calculator.service.impl.mappers.StoreMapper;

public class StoreServiceImpl implements StoreService {

    private StoreDAO storeDao;

    public StoreServiceImpl(StoreDAO storeDao) {
        this.storeDao = storeDao;
    }

    public StoreDTO getStoreById(Long id) {
        Store store = storeDao.getStoreById(id);
        return (store == null) ? null : StoreMapper.toDTO(store);
    }
    
    public Long save(StoreDTO storeDto) {
        return storeDao.save(StoreMapper.toDomainObject(storeDto));
    }
    
    public int update(StoreDTO storeDto) {
        return storeDao.update(StoreMapper.toDomainObject(storeDto));
    }

    public int delete(Long id) {
        return storeDao.deleteById(id);
    }

}
