package com.softserveinc.balance_calculator.repository.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.softserveinc.balance_calculator.domain.ConsumptionTransaction;
import com.softserveinc.balance_calculator.repository.ConsumptionTransactionDAO;
import com.softserveinc.balance_calculator.repository.exception.RepositoryException;
import com.softserveinc.balance_calculator.repository.impl.namespaces.TransactionNamespace;

/**
 * Default implementation for <code>ConsumptionTransactionDAO</code> entities.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 06/03/2017
 *
 */
public class ConsumptionTransactionDAOImpl implements ConsumptionTransactionDAO {
    
    private final String INSERT;
    private JdbcTemplate jdbcTemplate;
    
    protected ConsumptionTransactionDAOImpl(JdbcTemplate jdbcTemplate) {
        INSERT = String.format(TransactionNamespace.CONSUMPTION_BATCH, TransactionNamespace.CONSUMPTION_TABLE_NAME,
                TransactionNamespace.REGISTER_ID_COLUMN_NAME,
                TransactionNamespace.CONSUMED_VALUE_COLUMN_NAME,
                TransactionNamespace.CREATED_BY_COLUMN_NAME);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int[] saveAll(List<ConsumptionTransaction> consumptions) throws RepositoryException {
        List<Object[]> batch = new LinkedList<>();
        consumptions.forEach(consumption -> batch.add(getBatchValues(consumption)));
        try {
            return jdbcTemplate.batchUpdate(INSERT, batch);
        } catch (DataAccessException e) {
            throw new RepositoryException(e);
        }
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