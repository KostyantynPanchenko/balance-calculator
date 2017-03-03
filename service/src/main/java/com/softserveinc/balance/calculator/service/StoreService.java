package com.softserveinc.balance.calculator.service;

import com.softserveinc.balance.calculator.dto.StoreDTO;
import com.softserveinc.balance.calculator.service.exception.ServiceException;

public interface StoreService {

    StoreDTO getStoreById(Long id) throws ServiceException;

    int save(StoreDTO storeDto) throws ServiceException;

    int update(StoreDTO storeDto) throws ServiceException;

    int delete(Long id) throws ServiceException;
}
