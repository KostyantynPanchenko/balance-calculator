package com.softserveinc.balance.calculator.repository.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.softserveinc.balance.calculator.domain.ContributionTransaction;
import com.softserveinc.balance.calculator.repository.ContributionTransactionDAO;
import com.softserveinc.balance.calculator.repository.exception.RepositoryException;
import com.softserveinc.balance.calculator.repository.impl.namespaces.TransactionNamespace;

/**
 * Default implementation for <code>ConsumptionTransactionDAO</code> entities.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 06/03/2017
 *
 */
public class ContributionTransactionDAOImpl implements ContributionTransactionDAO {
    
    private final String INSERT;
    private JdbcTemplate jdbcTemplate;
    
    protected ContributionTransactionDAOImpl(JdbcTemplate jdbcTemplate) {
        INSERT = String.format(TransactionNamespace.CONTRIBUTION_BATCH, TransactionNamespace.CONTRIBUTION_TABLE_NAME,
                TransactionNamespace.REGISTER_ID_COLUMN_NAME,
                TransactionNamespace.ORDER_GRANTED_VALUE_COLUMN_NAME,
                TransactionNamespace.CREATED_BY_COLUMN_NAME);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int[] saveAll(List<ContributionTransaction> contributions) throws RepositoryException {
        List<Object[]> batch = new LinkedList<>();
        contributions.forEach(contribution -> batch.add(getBatchValues(contribution)));
        try {
            return jdbcTemplate.batchUpdate(INSERT, batch);
        } catch (DataAccessException e) {
            throw new RepositoryException(e);
        }
    }

    /**
     * Prepares arguments for batch processing.
     * 
     * @param contribution <code>ContributionTransaction</code> object
     * @return an object holding values for batch processing
     */
    private Object[] getBatchValues(ContributionTransaction contribution) {
        return new Object[] {
                contribution.getRegisterId(),
                contribution.getOrderGrantedValue(),
                contribution.getCreatedBy()
        };
    }

}
