package balance.calculator.service;

import java.util.List;

import balance.calculator.dto.ConsumptionTransactionDTO;

/**
 * This interface defines method for batch processing of <code>ConsumptionTransaction</code> entities.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 06/03/2017
 */
public interface ConsumptionTransactionService {
    
    /**
     * Processes a request to save transactions in a batch.
     * 
     * @param consumptions      list of <code>ConsumptionTransactionDTO</code>
     * @return                  an array containing the numbers of rows affected by 
     *                          each update in the batch
     */
    int[] saveAll(List<ConsumptionTransactionDTO> consumptions);
    
}
