package com.softserveinc.balance.calculator.repository.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.softserveinc.balance.calculator.domain.Store;
import com.softserveinc.balance.calculator.repository.AbstractDAO;
import com.softserveinc.balance.calculator.repository.StoreDAO;
import com.softserveinc.balance.calculator.repository.exception.DataIntegrityViolationRepositoryException;
import com.softserveinc.balance.calculator.repository.exception.DomainEntityNotFoundException;
import com.softserveinc.balance.calculator.repository.exception.RepositoryException;
import com.softserveinc.balance.calculator.repository.impl.mappers.StorePreparedStatementCreator;
import com.softserveinc.balance.calculator.repository.impl.mappers.StoreRowMapper;
import com.softserveinc.balance.calculator.repository.impl.namespaces.StoreNamespace;

/**
 * Implementation of <code>StoreRepository</code> interface.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 05/03/2017
 */
public class StoreDAOImpl extends AbstractDAO<Store> implements StoreDAO {

    private final static StoreRowMapper MAPPER = new StoreRowMapper();
    
    public StoreDAOImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Store getStoreById(final Long id) throws RepositoryException {
        final String GET = String.format(StoreNamespace.SELECT, 
                StoreNamespace.ID_COLUMN_NAME,
                StoreNamespace.TENANT_ID_COLUMN_NAME,
                StoreNamespace.NAME_COLUMN_NAME,
                StoreNamespace.DESCRIPTION_COLUMN_NAME,
                StoreNamespace.TABLE_NAME,
                StoreNamespace.ID_COLUMN_NAME);
        try {
            return getById(GET, new Object[] {id}, MAPPER);
        } catch (EmptyResultDataAccessException empty) {
            throw new DomainEntityNotFoundException(empty);
        } catch (DataAccessException e) {
            throw new RepositoryException(e);
        }
    }
    
    @Override
    public Long save(Store store) throws RepositoryException {
        try {
            return create(new StorePreparedStatementCreator(store));
        } catch (DataIntegrityViolationException violation) {
            throw new DataIntegrityViolationRepositoryException(violation.getMessage(), violation);
        } catch (DataAccessException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public int update(Store store) throws RepositoryException {
        final String UPDATE = String.format(StoreNamespace.UPDATE,
                StoreNamespace.TABLE_NAME,
                StoreNamespace.TENANT_ID_COLUMN_NAME,
                StoreNamespace.NAME_COLUMN_NAME,
                StoreNamespace.DESCRIPTION_COLUMN_NAME,
                StoreNamespace.ID_COLUMN_NAME);
        try {
            return execute(UPDATE, new Object[] {store.getTenantId(), store.getName(), store.getDescription(), store.getId()});
        } catch (DataIntegrityViolationException violation) {
            throw new DataIntegrityViolationRepositoryException(violation);
        } catch (DataAccessException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public int deleteById(Long id) throws RepositoryException {
        final String DELETE  = String.format(StoreNamespace.DELETE, 
                StoreNamespace.TABLE_NAME,
                StoreNamespace.ID_COLUMN_NAME);
        try {
            return execute(DELETE, new Object[] {id});
        } catch (DataIntegrityViolationException violation) {
            throw new DataIntegrityViolationRepositoryException(violation.getMessage(), violation);
        } catch (DataAccessException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }

}
