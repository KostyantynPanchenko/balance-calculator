package com.softserveinc.balance.calculator.service.impl;

import com.softserveinc.balance.calculator.repository.TransactionAllocationDAO;
import com.softserveinc.balance.calculator.service.TransactionAllocationService;

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
