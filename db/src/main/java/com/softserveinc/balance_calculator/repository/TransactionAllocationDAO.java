package com.softserveinc.balance_calculator.repository;

/**
 * Transaction allocation and balance calculation.
 * 
 * @author Kostyantyn Panchenko
 *
 */
public interface TransactionAllocationDAO {

    /**
     * Register allocations and calculate balance.
     * 
     * @param registerId id of <code>Register</code> for which to execute stored procedure.
     */
    void registerAllocation(Long registerId);
}
