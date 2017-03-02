package com.softserveinc.balance.calculator.repository.impl;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.softserveinc.balance.calculator.domain.Store;
import com.softserveinc.balance.calculator.repository.StoreDAO;

public class JdbcStoreDAO implements StoreDAO {
    
    private final String INSERT = "insert into stores(tenant_id, name, description) values(?, ?, ?)";
    private final String GET_BY_ID = "select id, tenant_id, name, description from stores where id = ?";
    private final String UPDATE = "update stores set tenant_id = ?, name = ?, description = ? where id = ?";
    private final String DELETE = "delete from stores where id = ?"; 
    private JdbcTemplate template;
    
    public void setDataSource(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    public int save(final Store store) {
        return this.template.update(INSERT, new Object[] {store.getTenantId(), store.getName(), store.getDescription()});
    }

    public Store getStoreById(final Long id) {
        Store store = null;
        try {
            store = (Store) template.queryForObject(GET_BY_ID, new Object[]{id}, new StoreRowMapper());
        } catch (EmptyResultDataAccessException e) {
            // nothing we can do except log it
        }
        return store;
    }

    public int update(Store store, Long id) {
        return this.template.update(UPDATE, new Object[] {store.getTenantId(), store.getName(), store.getDescription(), id});
    }

    public int deleteById(long id) {
        return this.template.update(DELETE, id);
    }
}

