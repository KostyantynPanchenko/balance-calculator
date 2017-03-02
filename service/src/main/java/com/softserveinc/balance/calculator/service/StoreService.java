package com.softserveinc.balance.calculator.service;

import com.softserveinc.balance.calculator.dto.StoreDTO;

public interface StoreService {

    StoreDTO getStoreById(Long id);
}
