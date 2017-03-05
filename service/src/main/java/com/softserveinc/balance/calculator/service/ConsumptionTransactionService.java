package com.softserveinc.balance.calculator.service;

import java.util.List;

import com.softserveinc.balance.calculator.dto.ConsumptionTransactionDTO;

public interface ConsumptionTransactionService {
    
    int[] saveAll(List<ConsumptionTransactionDTO> consumptions);

}
