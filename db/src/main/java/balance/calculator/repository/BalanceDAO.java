package balance.calculator.repository;

import java.time.LocalDate;

import balance.calculator.domain.Balance;

/**
 * This interface provide methods for obtaining balance for specified <code>Register</code>
 * for specified date.
 * 
 * @author Kostyantyn Pancheko
 * @version 1.0
 * @since 09/03/2017
 *
 */
public interface BalanceDAO {

    /**
     * Returns the last one calculated balance for <code>Register</code> with specified id.
     * 
     * @param registerId id of <code>Register</code> for which balance should be retrieved
     * @return <code>Balance</code> object 
     */
    Balance getCurrentBalance(Long registerId);

    /**
     * Returns balance for specified <code>Register</code> on specified date.
     * 
     * @param registerId id of <code>Register</code> for which balance should be retrieved
     * @param date date for which balance should be retrieved
     * @return <code>Balance</code> object
     */
    Balance getBalanceForDate(Long registerId, LocalDate date);

}
