package com.softserveinc.balance_calculator.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.softserveinc.balance_calculator.repository.exception.RepositoryException;

/**
 * Abstract class which provides CRUD operations for domain model classes.
 * 
 * @author Kostyantyn Panchenko
 * 
 * @param <T> type of domain object
 */
public abstract class AbstractDAO<T> {

    private JdbcTemplate template;
    
    protected AbstractDAO(JdbcTemplate jdbcTemplate) {
        template = jdbcTemplate;
    }
    
    /**
     * Retrieves entity by given id.
     * 
     * @param query select SQL query
     * @param params parameters for PreparedStatement placeholders substitution
     * @param rowMapper instance of <code>RPwMapper</code>
     * @return retrieved entity of type T
     */
    protected T getById(String query, Object[] params, RowMapper<T> rowMapper) {
        return (T) template.queryForObject(query, params, rowMapper);
    }
    
    /**
     * Creates a new entity in database.
     * 
     * @param psc instance of PreparedStatementCreator
     * @return auto generated key of newly created entity
     * @see PreparedStatementCreator
     */
    protected Long create(PreparedStatementCreator psc) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(psc, keyHolder);
        return keyHolder.getKey().longValue();
    }
    
    /**
     * Executes create/update/delete query based on given input.
     * Delegated call to JdbcTemplate#update(String sql, Object... args)
     * 
     * @param SQL       query to be executed
     * @param params    parameters for PreparedStatement placeholders substitution
     * @return          number of modified rows
     * @throws RepositoryException if could nod execute given query
     * @see             JdbcTemplate#update(String sql, Object... args)
     */
    protected int execute(String SQL, Object[] params) throws RepositoryException {
        return template.update(SQL, params);
    }
}
