package com.softserveinc.balance_calculator.repository;

import java.util.List;

import com.softserveinc.balance_calculator.domain.ContributionTransaction;
import com.softserveinc.balance_calculator.repository.exception.RepositoryException;

/**
 * Provides batch processing capability for <code>ContributionTransaction</code> entities.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 06/03/2017
 *
 */
public interface ContributionTransactionDAO {

    /**
     * Saves entities in a batch.
     * 
     * @param input list of <code>ContributionTransaction</code>
     * @return an array containing the numbers of rows affected by each update in the batch
     * @throws RepositoryException if error occurred during batch processing
     */
    int[] saveAll(List<ContributionTransaction> input) throws RepositoryException;

}
