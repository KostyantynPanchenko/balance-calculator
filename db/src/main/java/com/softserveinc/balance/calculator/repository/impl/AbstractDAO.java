package com.softserveinc.balance.calculator.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 * Abstract class which provides CRUD operations for domain model classes.
 * 
 * @author Kostyantyn Panchenko
 */
public abstract class AbstractDAO {

    protected JdbcTemplate template;
    
    protected AbstractDAO(JdbcTemplate jdbcTemplate) {
        template = jdbcTemplate;
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
    
}
