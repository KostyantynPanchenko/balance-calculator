package com.softserveinc.balance_calculator.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.softserveinc.balance_calculator.domain.ConsumptionTransaction;
import com.softserveinc.balance_calculator.dto.ConsumptionTransactionDTO;
import com.softserveinc.balance_calculator.repository.ConsumptionTransactionDAO;
import com.softserveinc.balance_calculator.repository.exception.RepositoryException;
import com.softserveinc.balance_calculator.service.ConsumptionTransactionService;
import com.softserveinc.balance_calculator.service.exceptions.ServiceException;
import com.softserveinc.balance_calculator.service.impl.mappers.ConsumptionMapper;

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
    public int[] saveAll(List<ConsumptionTransactionDTO> consumptions) throws ServiceException {
        List<ConsumptionTransaction> input = new ArrayList<>(consumptions.size());
        consumptions.forEach(consumption -> input.add(ConsumptionMapper.toDomainObject(consumption)));
        try {
            return consumptionDao.saveAll(input);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
    
}
