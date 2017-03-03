package com.softserveinc.balance.calculator.service;

import com.softserveinc.balance.calculator.dto.RegisterDTO;

public interface RegisterService {

    RegisterDTO getRegisterById(Long registerId);

    int save(RegisterDTO registerDto, Long storeId);

    int update(RegisterDTO registerDto, Long storeId, Long registerId);

    int delete(Long registerId, Long registerId2);
}
