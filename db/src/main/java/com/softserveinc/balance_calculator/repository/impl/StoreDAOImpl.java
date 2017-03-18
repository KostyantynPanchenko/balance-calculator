package com.softserveinc.balance_calculator.repository.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.softserveinc.balance_calculator.domain.Store;
import com.softserveinc.balance_calculator.repository.AbstractDAO;
import com.softserveinc.balance_calculator.repository.StoreDAO;
import com.softserveinc.balance_calculator.repository.exception.DataIntegrityViolationRepositoryException;
import com.softserveinc.balance_calculator.repository.exception.RepositoryException;
import com.softserveinc.balance_calculator.repository.impl.mappers.StorePreparedStatementCreator;
import com.softserveinc.balance_calculator.repository.impl.mappers.StoreRowMapper;
import com.softserveinc.balance_calculator.repository.impl.namespaces.StoreNamespace;

/**
 * Implementation of <code>StoreRepository</code> interface.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 05/03/2017
 */
public class StoreDAOImpl extends AbstractDAO<Store> implements StoreDAO {
    
    public StoreDAOImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Store getStoreById(final Long id) throws RepositoryException {
        try {
            return getById(StoreNamespace.SELECT, new Object[] {id}, new StoreRowMapper());
        } catch (EmptyResultDataAccessException empty) {
            return null;
        } catch (DataAccessException e) {
            throw new RepositoryException(e);
        }
    }
    
    @Override
    public Long save(Store store) throws DataIntegrityViolationRepositoryException, RepositoryException {
        try {
            return create(new StorePreparedStatementCreator(store));
        } catch (DataIntegrityViolationException violation) {
            throw new DataIntegrityViolationRepositoryException(violation.getMessage(), violation);
        } catch (DataAccessException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public int update(Store store) throws DataIntegrityViolationRepositoryException, RepositoryException {
        try {
            return template.update(StoreNamespace.UPDATE, new Object[] {store.getName(), store.getDescription(), store.getId()});
        } catch (DataIntegrityViolationException violation) {
            throw new DataIntegrityViolationRepositoryException(violation);
        } catch (DataAccessException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public int deleteById(Long id) throws DataIntegrityViolationRepositoryException, RepositoryException {
        try {
            return template.update(StoreNamespace.DELETE, new Object[] {id});
        } catch (DataIntegrityViolationException violation) {
            throw new DataIntegrityViolationRepositoryException(violation.getMessage(), violation);
        } catch (DataAccessException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }

}
