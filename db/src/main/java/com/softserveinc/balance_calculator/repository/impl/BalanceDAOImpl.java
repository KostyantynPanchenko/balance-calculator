package com.softserveinc.balance_calculator.repository.impl;

import java.time.LocalDate;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.softserveinc.balance_calculator.domain.Balance;
import com.softserveinc.balance_calculator.repository.BalanceDAO;
import com.softserveinc.balance_calculator.repository.exception.RepositoryException;
import com.softserveinc.balance_calculator.repository.impl.mappers.BalanceRowMapper;
import com.softserveinc.balance_calculator.repository.impl.namespaces.BalanceNamespace;

public class BalanceDAOImpl implements BalanceDAO {
    
    private JdbcTemplate jdbcTemplate;
    
    public BalanceDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Balance getCurrentBalance(Long registerId) throws RepositoryException {
        try {
            return jdbcTemplate.queryForObject(BalanceNamespace.GET_CURRENT_BALANCE, new BalanceRowMapper(), new Object[] {registerId});
        } catch (EmptyResultDataAccessException empty) {
            return null;
        } catch (DataAccessException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public Balance getBalanceForDate(Long registerId, LocalDate date) throws RepositoryException {
        try {
            return jdbcTemplate.queryForObject(BalanceNamespace.GET_BALANCE_FOR_DATE, new BalanceRowMapper(), new Object[] {registerId, date});
        } catch (EmptyResultDataAccessException empty) {
            return null;
        } catch (DataAccessException e) {
            throw new RepositoryException(e);
        }
    }
}
