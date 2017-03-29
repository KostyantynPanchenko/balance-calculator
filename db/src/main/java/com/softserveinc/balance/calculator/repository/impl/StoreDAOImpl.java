package com.softserveinc.balance.calculator.repository.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.softserveinc.balance.calculator.domain.Store;
import com.softserveinc.balance.calculator.repository.AbstractDAO;
import com.softserveinc.balance.calculator.repository.StoreDAO;
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
public class StoreDAOImpl extends AbstractDAO implements StoreDAO {
    
    public StoreDAOImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Store getStoreById(final Long id) {
        try {
            return (Store) template.queryForObject(StoreNamespace.SELECT, new Object[] {id}, new StoreRowMapper());
        } catch (EmptyResultDataAccessException empty) {
            return null;
        }
    }
    
    @Override
    public Long save(Store store) {
        return create(new StorePreparedStatementCreator(store));
    }

    @Override
    public int update(Store store) {
        return template.update(StoreNamespace.UPDATE, new Object[] {store.getName(), store.getDescription(), store.getId()});
    }

    @Override
    public int deleteById(Long id) {
        return template.update(StoreNamespace.DELETE, new Object[] {id});
    }

}
