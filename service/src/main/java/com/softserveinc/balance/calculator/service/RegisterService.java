package com.softserveinc.balance.calculator.service;

import com.softserveinc.balance.calculator.dto.RegisterDTO;
import com.softserveinc.balance.calculator.service.exception.ServiceException;

public interface RegisterService {

    RegisterDTO getRegisterById(Long registerId) throws ServiceException;

    int save(RegisterDTO registerDto, Long storeId) throws ServiceException;

    int update(RegisterDTO registerDto, Long storeId, Long registerId) throws ServiceException;

    int delete(Long registerId, Long registerId2) throws ServiceException;
}
