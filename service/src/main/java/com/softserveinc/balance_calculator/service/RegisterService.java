package com.softserveinc.balance_calculator.service;

import com.softserveinc.balance_calculator.dto.RegisterDTO;
import com.softserveinc.balance_calculator.service.exception.DataIntegrityViolationServiceException;
import com.softserveinc.balance_calculator.service.exception.EntityNotFoundServiceException;
import com.softserveinc.balance_calculator.service.exception.ServiceException;

/**
 * An interface holding collection of methods for processing 
 * CRUD operations on <code>Register</code> domain objects.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 05/03/2017
 */
public interface RegisterService {

    /**
     * Processes request to retrieve a <code>Register</code> entity
     * with given id.
     * <p>
     * It receives a <code>Register</code> entity as a result of a call 
     * to <code>RegisterRepository</code> object and transforms it to 
     * <code>RegisterDTO</code> object.
     * @param storeId           id of <code>Store</code> entity to which
     *                          requested <code>Register</code> entity
     *                          relates to
     * @param registerId        id of requested <code>Register</code> entity
     * @return                  <code>RegisterDTO</code> object representing 
     *                          <code>Register</code> object
     * @throws EntityNotFoundServiceException when <code>Register</code> entity 
     *                          with specified id was not found
     * @throws ServiceException when could not execute SQL query
     */
    RegisterDTO getRegisterById(Long storeId, Long registerId) throws EntityNotFoundServiceException, ServiceException;

    /**
     * Processes request to create <code>Register</code> entity with 
     * given fields values.
     * <p>
     * It transforms given <code>RegisterDTO</code> object to the instance
     * of <code>Register</code> domain class and delegates a request to 
     * the instance of <code>RegisterRepository</code>.
     * 
     * @param registerDto       <code>RegisterDTO</code> object holding
     *                          info for creating appropriate <code>Register</code>
     *                          entity
     * @return                  auto generated primary key of created entity
     * @throws DataIntegrityViolationServiceException  when an attempt 
     * to insert or update data results in violation of an integrity constraint
     * @throws ServiceException when could not execute SQL query
     */
    Long save(RegisterDTO registerDto) throws DataIntegrityViolationServiceException, ServiceException;

    /**
     * Processes request to update <code>Register</code> entity with
     * given fiels values.
     * <p>
     * It transforms given <code>RegisterDTO</code> object to the instance
     * of <code>Register</code> domain class and delegates a request to 
     * the instance of <code>RegisterRepository</code>.
     * 
     * @param registerDto       <code>RegisterDTO</code> object holding
     *                          info for updating appropriate <code>Register</code>
     *                          entity
     * @return                  number of modified rows
     * @throws DataIntegrityViolationServiceException  when an attempt 
     * to insert or update data results in violation of an integrity constraint
     * @throws ServiceException when could not execute SQL query
     */
    int update(RegisterDTO registerDto) throws DataIntegrityViolationServiceException, ServiceException;

    /**
     * 
     * @param storeId           id of <code>Store</code> entity to which
     *                          specified <code>Register</code> entity
     *                          relates to
     * @param registerId        id of <code>Register</code> entity to be deleted
     * @return                  number of modified rows
     * @throws DataIntegrityViolationServiceException  when an attempt 
     * to insert or update data results in violation of an integrity constraint
     * @throws ServiceException when could not execute SQL query
     */
    int delete(Long storerId, Long registerId) throws DataIntegrityViolationServiceException, ServiceException;
}