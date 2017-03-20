package com.softserveinc.balance.calculator.repository.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.softserveinc.balance.calculator.domain.ContributionTransaction;
import com.softserveinc.balance.calculator.repository.ContributionTransactionDAO;
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

    private JdbcTemplate jdbcTemplate;
    
    protected ContributionTransactionDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int[] saveAll(List<ContributionTransaction> contributions) {
        List<Object[]> batch = new LinkedList<>();
        contributions.forEach(contribution -> batch.add(getBatchValues(contribution)));
        return jdbcTemplate.batchUpdate(TransactionNamespace.CONTRIBUTION_BATCH, batch);
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
