package com.softserveinc.balance_calculator.service;

import com.softserveinc.balance_calculator.dto.StoreDTO;
import com.softserveinc.balance_calculator.service.exceptions.DataIntegrityViolationServiceException;
import com.softserveinc.balance_calculator.service.exceptions.ServiceException;

/**
 * An interface holding a collection of methods for processing 
 * CRUD operations on <code>Store</code> domain objects.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 05/03/2017
 */
public interface StoreService {

    /**
     * Processes request to retrieve a <code>Store</code> entity
     * with given id.
     * <p>
     * It receives a <code>Store</code> entity as a result of a call 
     * to <code>StoreRepository</code> object and transforms it to 
     * <code>StoreDTO</code> object.
     * 
     * @param id                id of requested <code>Store</code> entity
     * @return                  <code>StoreDTO</code> object representing 
     *                          <code>Store</code> object
     * @throws ServiceException when could not execute SQL query
     */
    StoreDTO getStoreById(Long id) throws ServiceException;

    /**
     * Processes request to create <code>Store</code> entity with 
     * given fields values.
     * <p>
     * It transforms given <code>StoreDTO</code> object to the instance
     * of <code>Store</code> domain class and delegates a request to 
     * the instance of <code>StoreRepository</code>.
     * 
     * @param storeDto  <code>StoreDTO</code> object extracted from 
     *                  request
     * @return          auto generated primary key of created entity
     * @throws DataIntegrityViolationServiceException  when an attempt 
     * to insert or update data results in violation of an integrity constraint
     * @throws ServiceException when could not execute SQL query
     */
    Long save(StoreDTO storeDto) throws DataIntegrityViolationServiceException, ServiceException;

    /**
     * Processes request to update <code>Store</code> entity with 
     * given fields values.
     * <p>
     * It transforms given <code>StoreDTO</code> object to the instance
     * of <code>Store</code> domain class and delegates a request to 
     * the instance of <code>StoreRepository</code>.
     * 
     * @param storeDto  <code>StoreDTO</code> object extracted from 
     *                  request
     * @return          number of modified rows
     * @throws DataIntegrityViolationServiceException  when an attempt 
     * to insert or update data results in violation of an integrity constraint
     * @throws ServiceException when could not execute SQL query
     */
    int update(StoreDTO storeDto) throws DataIntegrityViolationServiceException, ServiceException;

    /**
     * Processes request to delete <code>Store</code> entity with given id.
     * 
     * @param id        id of <code>Store</code> entity to be deleted
     * @return          number of modified rows
     * @throws DataIntegrityViolationServiceException  when an attempt 
     * to insert or update data results in violation of an integrity constraint
     * @throws ServiceException when could not execute SQL query
     */
    int delete(Long id) throws DataIntegrityViolationServiceException, ServiceException;
    
}
