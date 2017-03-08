package com.softserveinc.balance.calculator.repository;

import com.softserveinc.balance.calculator.domain.Store;
import com.softserveinc.balance.calculator.repository.exception.DataIntegrityViolationRepositoryException;
import com.softserveinc.balance.calculator.repository.exception.DomainEntityNotFoundException;
import com.softserveinc.balance.calculator.repository.exception.RepositoryException;

/**
 * Provides CRUD operations for <code>Store</code> domain objects.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 05/03/2017
 */
public interface StoreDAO {
    
    /**
     * Retrieves a <code>Store</code> entity with specified id.
     * 
     * @param id    id of <code>Store</code> entity to be retrieved
     * @return      retrieved <code>Store</code> entity
     * @throws DomainEntityNotFoundException when entity not found
     * @throws RepositoryException when could not execute SQL query
     */
    Store getStoreById(Long id) throws DomainEntityNotFoundException, RepositoryException;
    
    /**
     * Saves given <code>Store</code> entity.
     * 
     * @param store </code>Store</code> entity to be saved
     * @return      auto generated key of created entity
     * @throws DataIntegrityViolationRepositoryException when an attempt to insert or 
     *              update data results in violation of an integrity constraint
     * @throws RepositoryException if could not execute SQL query
     */
    Long save(Store store) throws DataIntegrityViolationRepositoryException, RepositoryException;
    
    /**
     * Updates given <code>Store</code> entity.
     * 
     * @param store <code>Store</code> entity to be updated
     * @return      number of modified rows
     * @throws DataIntegrityViolationRepositoryException when an attempt to insert or 
     *              update data results in violation of an integrity constraint
     * @throws RepositoryException if could not execute SQL query
     */
    int update(Store store) throws DataIntegrityViolationRepositoryException, RepositoryException;
    
    /**
     * Deletes a <code>Store</code> entity.
     * 
     * @param id    id of <code>Store</code> entity to be deleted
     * @return      number of modified rows
     * @throws DataIntegrityViolationRepositoryException when an attempt to insert or 
     *              update data results in violation of an integrity constraint
     * @throws RepositoryException if could not execute SQL query
     */
    int deleteById(Long id) throws DataIntegrityViolationRepositoryException, RepositoryException;

}
