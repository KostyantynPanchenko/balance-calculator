package com.softserveinc.balance.calculator.repository.impl;

import static com.softserveinc.balance.calculator.repository.impl.namespaces.TransactionNamespace.CONTRIBUTION_BATCH;

import org.springframework.jdbc.core.JdbcTemplate;

import com.softserveinc.balance.calculator.domain.ContributionTransaction;
import com.softserveinc.balance.calculator.domain.Transaction;
import com.softserveinc.balance.calculator.repository.ContributionTransactionDAO; 

/**
 * Default implementation for <code>ConsumptionTransactionDAO</code> entities.
 * 
 * @author Kostyantyn Panchenko
 * @version 2.0
 * @since 10/04/2017
 *
 */
public class ContributionTransactionDAOImpl extends AbstractTransactionDAO<ContributionTransaction> implements ContributionTransactionDAO {
    
    public ContributionTransactionDAOImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
        this.batchSQL = CONTRIBUTION_BATCH;
    }

    @Override
    Object[] getBatchValues(Transaction transaction) {
        ContributionTransaction contribution = (ContributionTransaction) transaction;
        return new Object[] {
              contribution.getRegisterId(),
              contribution.getOrderGrantedValue(),
              contribution.getCreatedBy()
      };
    }

}
