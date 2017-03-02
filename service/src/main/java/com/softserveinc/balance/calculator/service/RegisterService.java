package com.softserveinc.balance.calculator.service;

import com.softserveinc.balance.calculator.dto.RegisterDTO;

public interface RegisterService {

    RegisterDTO getRegisterById(Long storeId, Long registerId);

    int save(RegisterDTO registerDto);

    int update(RegisterDTO registerDto, Long storeId, Long registerId);

    int delete(Long storeId, Long registerId);
}
