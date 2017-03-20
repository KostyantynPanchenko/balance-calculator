package com.softserveinc.balance.calculator.repository;

import java.util.List;

import com.softserveinc.balance.calculator.domain.ConsumptionTransaction;

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
     */
    int[] saveAll(List<ConsumptionTransaction> consumptions);

}
