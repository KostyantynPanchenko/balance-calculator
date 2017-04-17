package balance.calculator.service.impl;

import java.util.ArrayList;
import java.util.List;

import balance.calculator.domain.ConsumptionTransaction;
import balance.calculator.dto.ConsumptionTransactionDTO;
import balance.calculator.repository.ConsumptionTransactionDAO;
import balance.calculator.service.ConsumptionTransactionService;
import balance.calculator.service.impl.mappers.ConsumptionMapper;

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
