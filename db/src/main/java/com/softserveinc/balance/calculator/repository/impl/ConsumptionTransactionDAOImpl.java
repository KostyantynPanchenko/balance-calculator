package com.softserveinc.balance.calculator.repository.impl;

import static com.softserveinc.balance.calculator.repository.impl.namespaces.TransactionNamespace.CONSUMPTION_BATCH;

import org.springframework.jdbc.core.JdbcTemplate;

import com.softserveinc.balance.calculator.domain.ConsumptionTransaction;
import com.softserveinc.balance.calculator.domain.Transaction;
import com.softserveinc.balance.calculator.repository.ConsumptionTransactionDAO;

/**
 * Default implementation for <code>ConsumptionTransactionDAO</code> entities.
 * 
 * @author Kostyantyn Panchenko
 * @version 2.0
 * @since 10/04/2017
 *
 */
public class ConsumptionTransactionDAOImpl extends AbstractTransactionDAO<ConsumptionTransaction> implements ConsumptionTransactionDAO {
    
    public ConsumptionTransactionDAOImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
        this.batchSQL = CONSUMPTION_BATCH;
    }

    @Override
    Object[] getBatchValues(Transaction transaction) {
        ConsumptionTransaction consumption = (ConsumptionTransaction) transaction;
        return new Object[] {
                consumption.getRegisterId(),
                consumption.getConsumedValue(),
                consumption.getCreatedBy()
        };
    }

}
