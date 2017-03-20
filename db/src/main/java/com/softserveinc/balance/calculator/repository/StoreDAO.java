package com.softserveinc.balance.calculator.repository;

import com.softserveinc.balance.calculator.domain.Store;

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
     */
    Store getStoreById(Long id);
    
    /**
     * Saves given <code>Store</code> entity.
     * 
     * @param store </code>Store</code> entity to be saved
     * @return      auto generated key of created entity
     */
    Long save(Store store);
    
    /**
     * Updates given <code>Store</code> entity.
     * 
     * @param store <code>Store</code> entity to be updated
     * @return      number of modified rows
     */
    int update(Store store);
    
    /**
     * Deletes a <code>Store</code> entity.
     * 
     * @param id    id of <code>Store</code> entity to be deleted
     * @return      number of modified rows
     */
    int deleteById(Long id);

}
