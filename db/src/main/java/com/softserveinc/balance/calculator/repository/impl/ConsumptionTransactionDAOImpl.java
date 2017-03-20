package com.softserveinc.balance.calculator.repository.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.softserveinc.balance.calculator.domain.ConsumptionTransaction;
import com.softserveinc.balance.calculator.repository.ConsumptionTransactionDAO;
import com.softserveinc.balance.calculator.repository.impl.namespaces.TransactionNamespace;

/**
 * Default implementation for <code>ConsumptionTransactionDAO</code> entities.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 06/03/2017
 *
 */
public class ConsumptionTransactionDAOImpl implements ConsumptionTransactionDAO {

    private JdbcTemplate jdbcTemplate;
    
    protected ConsumptionTransactionDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int[] saveAll(List<ConsumptionTransaction> consumptions) {
        List<Object[]> batch = new LinkedList<>();
        consumptions.forEach(consumption -> batch.add(getBatchValues(consumption)));
        return jdbcTemplate.batchUpdate(TransactionNamespace.CONSUMPTION_BATCH, batch);
    }

    /**
     * Prepares arguments for batch processing.
     * 
     * @param consumption <code>ConsumptionTransaction</code> object
     * @return an object holding values for batch processing
     */
    private Object[] getBatchValues(ConsumptionTransaction consumption) {
        return new Object[] {
                consumption.getRegisterId(),
                consumption.getConsumedValue(),
                consumption.getCreatedBy()
        };
    }

}
