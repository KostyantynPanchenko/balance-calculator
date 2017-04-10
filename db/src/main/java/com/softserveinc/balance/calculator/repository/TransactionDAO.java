package com.softserveinc.balance.calculator.repository;

import java.util.List;

/**
 * Provides generic batch processing capability for <code>Transaction</code> entities.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 10/04/2017
 *
 */
public interface TransactionDAO<T> {

    /**
     * Saves entities in a batch.
     * 
     * @param input list of <code>Transaction</code>s
     * @return an array containing the numbers of rows affected by each update in the batch
     */
    int[] saveAll(List<T> input);

}
