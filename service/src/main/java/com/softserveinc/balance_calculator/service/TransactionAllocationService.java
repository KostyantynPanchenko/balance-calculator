package com.softserveinc.balance_calculator.service;

/**
 * Transaction allocation and balance calculation service.
 * 
 * @author Kostyantyn Panchenko
 *
 */
public interface TransactionAllocationService {

    /**
     * Register allocations and calculate balance.
     * 
     * @param registerId id of <code>Register</code> for which to execute stored procedure.
     */
    void registerAllocation(Long registerId);
    
}
