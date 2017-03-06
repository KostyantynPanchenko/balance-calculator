package com.softserveinc.balance.calculator.service;

import java.util.List;

import com.softserveinc.balance.calculator.dto.ConsumptionTransactionDTO;
import com.softserveinc.balance.calculator.service.exception.ServiceException;

public interface ConsumptionTransactionService {
    
    int[] saveAll(List<ConsumptionTransactionDTO> consumptions) throws ServiceException;

}
