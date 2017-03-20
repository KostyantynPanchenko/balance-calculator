package com.softserveinc.balance.calculator.repository;

import java.util.List;

import com.softserveinc.balance.calculator.domain.ContributionTransaction;

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
     */
    int[] saveAll(List<ContributionTransaction> input);

}
