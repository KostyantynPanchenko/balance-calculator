package com.softserveinc.balance.calculator.repository;

import com.softserveinc.balance.calculator.domain.Register;
import com.softserveinc.balance.calculator.repository.exception.DataIntegrityViolationRepositoryException;
import com.softserveinc.balance.calculator.repository.exception.DomainEntityNotFoundException;
import com.softserveinc.balance.calculator.repository.exception.RepositoryException;

/**
 * Provides CRUD operations for <code>Register</code> domain objects.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 05/03/2017
 */
public interface RegisterDAO {

    /**
     * Retrieves a <code>Register</code> entity with specified id which relates
     * to appropriate <code>Store</code> entity.
     * 
     * @param storeId       id of <code>Store</code> entity to which
     *                      <code>Register</code> entity relates
     * @param registerId    id of <code>Register</code> entity to be
     *                      retrieved
     * @return              retrieved <code>Register</code> entity
     * @throws DomainEntityNotFoundException when entity was not found 
     * @throws RepositoryException when could not execute SQL query
     */
    Register getRegisterById(Long storeId, Long registerId) throws DomainEntityNotFoundException, RepositoryException;
    
    /**
     * Saves given <code>Register</code> entity.
     * 
     * @param register  <code>Register</code> entity to be saved
     * @return          auto generated key of created entity
     * @throws DataIntegrityViolationRepositoryException when an attempt to insert or 
     *                  update data results in violation of an integrity constraint
     * @throws RepositoryException if could not save entity
     */
    Long insert(Register register) throws DataIntegrityViolationRepositoryException, RepositoryException;
    
    /**
     * Updates given <code>Register</code> entity.
     * 
     * @param register  <code>Register</code> entity to be updated
     * @return          number of modified rows
     * @throws DataIntegrityViolationRepositoryException when an attempt to insert or 
     *                  update data results in violation of an integrity constraint
     * @throws RepositoryException if could not update entity
     */
    int update(Register register) throws DataIntegrityViolationRepositoryException, RepositoryException;
    
    /**
     * Deletes <code>Register</code> entity.
     * 
     * @param storeId       id of <code>Store</code> entity to which
     *                      <code>Register</code> entity relates to
     * @param registerId    id of <code>Register</code> to be deleted
     * @return              number of modified rows
     * @throws DataIntegrityViolationRepositoryException when an attempt to insert or 
     *                      update data results in violation of an integrity constraint
     * @throws RepositoryException if could not perform deletion
     */
    int delete(Long storeId, Long registerId) throws DataIntegrityViolationRepositoryException,RepositoryException;
}
