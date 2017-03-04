package com.softserveinc.balance.calculator.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.softserveinc.balance.calculator.domain.Store;
import com.softserveinc.balance.calculator.repository.StoreDAO;
import com.softserveinc.balance.calculator.repository.exception.DataIntegrityViolationRepositoryException;
import com.softserveinc.balance.calculator.repository.exception.DomainEntityNotFoundException;
import com.softserveinc.balance.calculator.repository.exception.RepositoryException;
import com.softserveinc.balance.calculator.repository.impl.mappers.StoreRowMapper;

public class StoreDAOImpl implements StoreDAO {

    private final String GET_BY_ID = "select id, tenant_id, name, description from stores where id = ?";
    private final String INSERT = "insert into stores(tenant_id, name, description) values(?, ?, ?)";
    private final String UPDATE = "update stores set tenant_id = ?, name = ?, description = ? where id = ?";
    private final String DELETE = "delete from stores where id = ?"; 
    private JdbcTemplate template;
    
    public void setDataSource(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    public Store getStoreById(final Long id) throws RepositoryException {
        try {
            return (Store) template.queryForObject(GET_BY_ID, new Object[]{id}, new StoreRowMapper());
        } catch (EmptyResultDataAccessException empty) {
            throw new DomainEntityNotFoundException();
        } catch (DataAccessException e) {
            throw new RepositoryException(e.getMessage());
        }
    }
    
    public Long save(Store store) throws RepositoryException {        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(
            new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(INSERT, new String[] {"id"});
                    ps.setLong(1, store.getTenantId());
                    ps.setString(2, store.getName());
                    ps.setString(3, store.getDescription());
                    return ps;
                }
            },
            keyHolder);
        return keyHolder.getKey().longValue();
    }

    public int update(Store store) throws RepositoryException {
        return execute(UPDATE, new Object[] {store.getTenantId(), store.getName(), store.getDescription(), store.getId()});
    }

    public int deleteById(Long id) throws RepositoryException {
        return execute(DELETE, new Object[] {id});
    }
    
    private int execute(String SQL, Object[] params) throws RepositoryException {
        try {
            return template.update(SQL, params);
        } catch (DataIntegrityViolationException violation) {
            throw new DataIntegrityViolationRepositoryException(violation.getMessage());
        } catch (DataAccessException e) {
            throw new RepositoryException(e.getMessage());
        }
    }
}

