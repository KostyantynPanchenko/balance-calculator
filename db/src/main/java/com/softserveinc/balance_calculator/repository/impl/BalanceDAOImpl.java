package com.softserveinc.balance_calculator.repository.impl;

import java.time.LocalDate;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.softserveinc.balance_calculator.domain.Balance;
import com.softserveinc.balance_calculator.repository.BalanceDAO;
import com.softserveinc.balance_calculator.repository.exception.DomainEntityNotFoundException;
import com.softserveinc.balance_calculator.repository.exception.RepositoryException;
import com.softserveinc.balance_calculator.repository.impl.mappers.BalanceRowMapper;
import com.softserveinc.balance_calculator.repository.impl.namespaces.BalanceNamespace;

public class BalanceDAOImpl implements BalanceDAO {
    
    private JdbcTemplate jdbcTemplate;
    
    public BalanceDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Balance getCurrentBalance(Long registerId) throws DomainEntityNotFoundException, RepositoryException {
        try {
            return jdbcTemplate.queryForObject(getCurrentBalanceSQL(), new BalanceRowMapper(), new Object[] {registerId});
        } catch (EmptyResultDataAccessException empty) {
            throw new DomainEntityNotFoundException(empty);
        } catch (DataAccessException e) {
            throw new RepositoryException(e);
        }
    }

    /**
     * Construct SQL query for retrieving current balance.
     * 
     * @return SQL query string
     */
    private String getCurrentBalanceSQL() {
        return String.format(BalanceNamespace.GET_CURRENT_BALANCE,
                BalanceNamespace.TABLE_NAME, BalanceNamespace.REGISTER_ID_COLUMN_NAME, BalanceNamespace.ID_COLUMN_NAME);
    }

    @Override
    public Balance getBalanceForDate(Long registerId, LocalDate date) throws DomainEntityNotFoundException, RepositoryException {
        try {
            return jdbcTemplate.queryForObject(getBalanceForDateSQL(), new BalanceRowMapper(), new Object[] {registerId, date});
        } catch (EmptyResultDataAccessException empty) {
            throw new DomainEntityNotFoundException(empty);
        } catch (DataAccessException e) {
            throw new RepositoryException(e);
        }
    }

    /**
     * Construct SQL query for retrieving current balance.
     * 
     * @return SQL query string
     */
    private String getBalanceForDateSQL() {
        return String.format(BalanceNamespace.GET_BALANCE_FOR_DATE,
                BalanceNamespace.TABLE_NAME, BalanceNamespace.REGISTER_ID_COLUMN_NAME,
                BalanceNamespace.CREATED_ON_COLUMN_NAME, BalanceNamespace.ID_COLUMN_NAME);
    }
}
