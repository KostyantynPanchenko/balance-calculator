package com.softserveinc.balance.calculator.service;

import java.time.LocalDate;

import com.softserveinc.balance.calculator.dto.BalanceDTO;
import com.softserveinc.balance.calculator.service.exception.EntityNotFoundServiceException;
import com.softserveinc.balance.calculator.service.exception.ServiceException;

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
     * @throws ServiceException when could not execute SQL query
     * @throws EntityNotFoundServiceException when balance was not found
     */
    BalanceDTO getCurrentBalance(Long registerId) throws EntityNotFoundServiceException, ServiceException;

    /**
     * Returns balance for specified <code>Register</code> on specified date.
     * 
     * @param registerId id of <code>Register</code> for which balance should be retrieved
     * @param date date for which balance should be retrieved
     * @return <code>BalanceDTO</code> object
     * @throws ServiceException when could not execute SQL query
     * @throws EntityNotFoundServiceException when balance was not found
     */
    BalanceDTO getBalanceForDate(Long registerId, LocalDate date) throws EntityNotFoundServiceException, ServiceException;

}
