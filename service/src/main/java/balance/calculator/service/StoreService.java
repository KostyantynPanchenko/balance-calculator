package balance.calculator.service;

import balance.calculator.dto.StoreDTO;

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
     */
    StoreDTO getStoreById(Long id);

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
     */
    Long save(StoreDTO storeDto);

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
     */
    int update(StoreDTO storeDto);

    /**
     * Processes request to delete <code>Store</code> entity with given id.
     * 
     * @param id        id of <code>Store</code> entity to be deleted
     * @return          number of modified rows
     */
    int delete(Long id);
    
}
