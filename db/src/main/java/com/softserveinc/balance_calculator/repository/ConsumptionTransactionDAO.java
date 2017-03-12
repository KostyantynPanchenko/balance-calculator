package com.softserveinc.balance_calculator.repository;

import java.util.List;

import com.softserveinc.balance_calculator.domain.ConsumptionTransaction;
import com.softserveinc.balance_calculator.repository.exception.RepositoryException;

/**
 * Provides batch processing capability for <code>ConsumptionTransaction</code> entities.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 06/03/2017
 *
 */
public interface ConsumptionTransactionDAO {
    
    /**
     * Saves entities in a batch.
     * 
     * @param input list of <code>ConsumptionTransaction</code>
     * @return an array containing the numbers of rows affected by each update in the batch
     * @throws RepositoryException if error occurred during batch processing
     */
    int[] saveAll(List<ConsumptionTransaction> consumptions) throws RepositoryException;

}
