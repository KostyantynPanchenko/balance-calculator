package com.softserveinc.balance.calculator.service;

import com.softserveinc.balance.calculator.dto.RegisterDTO;
import com.softserveinc.balance.calculator.service.exception.ServiceException;

public interface RegisterService {

    RegisterDTO getRegisterById(Long storeId, Long registerId) throws ServiceException;

    int save(RegisterDTO registerDto) throws ServiceException;

    int update(RegisterDTO registerDto) throws ServiceException;

    int delete(Long storerId, Long registerId) throws ServiceException;
}
