package com.softserveinc.balance.calculator.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.softserveinc.balance.calculator.domain.ConsumptionTransaction;
import com.softserveinc.balance.calculator.dto.ConsumptionTransactionDTO;
import com.softserveinc.balance.calculator.repository.ConsumptionTransactionDAO;
import com.softserveinc.balance.calculator.service.ConsumptionTransactionService;
import com.softserveinc.balance.calculator.service.impl.mappers.ConsumptionMapper;

/**
 * Implementation of <code>ConsumptionTransactionService</code>.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @see ConsumptionTransactionService
 * @since 06/03/2017
 */
public class ConsumptionTransactionServiceImpl implements ConsumptionTransactionService {

    private ConsumptionTransactionDAO consumptionDao;
    
    public ConsumptionTransactionServiceImpl(ConsumptionTransactionDAO consumptionDao) {
        this.consumptionDao = consumptionDao;
    }

    @Override
    public int[] saveAll(List<ConsumptionTransactionDTO> consumptions) {
        List<ConsumptionTransaction> transactions = new ArrayList<>(consumptions.size());
        consumptions.forEach(consumption -> transactions.add(ConsumptionMapper.toDomainObject(consumption)));
        return consumptionDao.saveAll(transactions);
    }
    
}
