package com.softserveinc.balance.calculator.service;

import java.util.List;

import com.softserveinc.balance.calculator.dto.ContributionTransactionDTO;

/**
 * This interface defines method for batch processing of <code>ConsumptionTransaction</code> entities.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 06/03/2017
 */
public interface ContributionTransactionService {

    /**
     * Processes a request to save transactions in a batch.
     * 
     * @param contributions     list of <code>ContributionTransactionDTO</code>
     * @return                  an array containing the numbers of rows affected by 
     *                          each update in the batch
     */
    int[] saveAll(List<ContributionTransactionDTO> contributions);
    
}
