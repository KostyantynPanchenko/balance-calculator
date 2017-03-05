package com.softserveinc.balance.calculator.service;

import com.softserveinc.balance.calculator.dto.RegisterDTO;
import com.softserveinc.balance.calculator.service.exception.ServiceException;

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
     * @throws ServiceException if <code>Register</code> entity with 
     *                          specified id was not found
     */
    RegisterDTO getRegisterById(Long storeId, Long registerId) throws ServiceException;

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
     * @throws ServiceException if could not save given object
     */
    Long save(RegisterDTO registerDto) throws ServiceException;

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
     * @throws ServiceException if could not execute update
     */
    int update(RegisterDTO registerDto) throws ServiceException;

    /**
     * 
     * @param storeId           id of <code>Store</code> entity to which
     *                          specified <code>Register</code> entity
     *                          relates to
     * @param registerId        id of <code>Register</code> entity to be deleted
     * @return                  number of modified rows
     * @throws ServiceException if could not execute delete
     */
    int delete(Long storerId, Long registerId) throws ServiceException;
}
