package com.softserveinc.balance.calculator.service;

import com.softserveinc.balance.calculator.dto.StoreDTO;

public interface StoreService {

    StoreDTO getStoreById(Long id);

    int update(StoreDTO storeDto, Long id);

    int delete(Long id);

    int save(StoreDTO storeDto);
}
