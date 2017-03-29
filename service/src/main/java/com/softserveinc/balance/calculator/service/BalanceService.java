package com.softserveinc.balance.calculator.service;

import java.time.LocalDate;

import com.softserveinc.balance.calculator.dto.BalanceDTO;

/**
 * This interface provide methods for obtaining balance for specified <code>Register</code>
 * for specified date.
 * 
 * @author Kostyantyn Pancheko
 * @version 1.0
 * @since 09/03/2017
 *
 */
public interface BalanceService {

    /**
     * Returns the last one calculated balance for <code>Register</code> with specified id.
     * 
     * @param registerId id of <code>Register</code> for which balance should be retrieved
     * @return <code>DalanceDTO</code> object
     */
    BalanceDTO getCurrentBalance(Long registerId);

    /**
     * Returns balance for specified <code>Register</code> on specified date.
     * 
     * @param registerId id of <code>Register</code> for which balance should be retrieved
     * @param date date for which balance should be retrieved
     * @return <code>BalanceDTO</code> object
     */
    BalanceDTO getBalanceForDate(Long registerId, LocalDate date);

}
