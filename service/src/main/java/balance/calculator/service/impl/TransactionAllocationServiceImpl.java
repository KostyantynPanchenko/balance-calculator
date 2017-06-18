package balance.calculator.service.impl;

import org.springframework.stereotype.Component;

import balance.calculator.repository.TransactionAllocationDAO;
import balance.calculator.service.TransactionAllocationService;

@Component
public class TransactionAllocationServiceImpl implements TransactionAllocationService {

    private TransactionAllocationDAO transactionAllocationDao;
    
    public TransactionAllocationServiceImpl(TransactionAllocationDAO transactionAllocationDao) {
        this.transactionAllocationDao = transactionAllocationDao;
    }
    @Override
    public void registerAllocation(Long registerId) {
        transactionAllocationDao.registerAllocation(registerId);
    }

}
