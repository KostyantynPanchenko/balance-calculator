package com.softserveinc.balance.calculator.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.softserveinc.balance.calculator.domain.ConsumptionTransaction;
import com.softserveinc.balance.calculator.dto.ConsumptionTransactionDTO;
import com.softserveinc.balance.calculator.repository.ConsumptionTransactionDAO;
import com.softserveinc.balance.calculator.service.ConsumptionTransactionService;

public class ConsumptionTransactionServiceImpl implements ConsumptionTransactionService {

    private ConsumptionTransactionDAO consumptionDao;
    
    public ConsumptionTransactionServiceImpl(ConsumptionTransactionDAO consumptionDao) {
        this.consumptionDao = consumptionDao;
    }

    @Override
    public int[] saveAll(List<ConsumptionTransactionDTO> consumptions) {
        List<ConsumptionTransaction> input = new ArrayList<>(consumptions.size());
        consumptions.forEach(consumption -> input.add(newTransaction(consumption)));
        return consumptionDao.saveAll(input);
    }

    private ConsumptionTransaction newTransaction(ConsumptionTransactionDTO consumption) {
        return new ConsumptionTransaction.Builder()
                .setRegisterId(consumption.getRegisterId())
                .setConsumedValue(consumption.getConsumedValue())
                .setCreatedBy(consumption.getCreatedBy())
                .build();
    }
}
