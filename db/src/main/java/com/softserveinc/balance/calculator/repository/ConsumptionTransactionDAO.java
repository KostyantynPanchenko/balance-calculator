package com.softserveinc.balance.calculator.repository;

import java.util.List;

import com.softserveinc.balance.calculator.domain.ConsumptionTransaction;

public interface ConsumptionTransactionDAO {
    
    int[] saveAll(List<ConsumptionTransaction> consumptions);

}
