package com.softserveinc.balance.calculator.repository;

import com.softserveinc.balance.calculator.domain.Store;
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
     * @throws RepositoryException if entity was not found
     */
    Store getStoreById(Long id) throws RepositoryException;
    
    /**
     * Saves given <code>Store</code> entity.
     * 
     * @param store </code>Store</code> entity to be saved
     * @return      auto generated key of created entity
     * @throws RepositoryException if could not create a new record
     */
    Long save(Store store) throws RepositoryException;
    
    /**
     * Updates given <code>Store</code> entity.
     * 
     * @param store <code>Store</code> entity to be updated
     * @return      number of modified rows
     * @throws RepositoryException if could not perform update
     */
    int update(Store store) throws RepositoryException;
    
    /**
     * Deletes a <code>Store</code> entity.
     * 
     * @param id    id of <code>Store</code> entity to be deleted
     * @return      number of modified rows
     * @throws RepositoryException if could not perform deletion
     */
    int deleteById(Long id) throws RepositoryException;

}
