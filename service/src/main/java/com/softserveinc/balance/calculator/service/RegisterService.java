package com.softserveinc.balance.calculator.service;

import com.softserveinc.balance.calculator.dto.RegisterDTO;

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
     * @param registerId        id of requested <code>Register</code> entity
     */
    RegisterDTO getRegisterById(Long registerId);

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
     */
    Long save(RegisterDTO registerDto);

    /**
     * Processes request to update <code>Register</code> entity with
     * given fields values.
     * <p>
     * It transforms given <code>RegisterDTO</code> object to the instance
     * of <code>Register</code> domain class and delegates a request to 
     * the instance of <code>RegisterRepository</code>.
     * 
     * @param registerDto       <code>RegisterDTO</code> object holding
     *                          info for updating appropriate <code>Register</code>
     *                          entity
     * @return                  number of modified rows
     */
    int update(RegisterDTO registerDto);

    /**
     * Deletes <code>Register</code> entity.
     * 
     * @param registerId        id of <code>Register</code> entity to be deleted
     * @return                  number of modified rows
     */
    int delete(Long registerId);
}
