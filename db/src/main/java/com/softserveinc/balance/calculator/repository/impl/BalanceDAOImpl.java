package com.softserveinc.balance.calculator.repository.impl;

import java.time.LocalDate;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.softserveinc.balance.calculator.domain.Balance;
import com.softserveinc.balance.calculator.repository.BalanceDAO;
import com.softserveinc.balance.calculator.repository.impl.mappers.BalanceRowMapper;
import static com.softserveinc.balance.calculator.repository.impl.namespaces.BalanceNamespace.*;

public class BalanceDAOImpl implements BalanceDAO {

    private JdbcTemplate jdbcTemplate;

    public BalanceDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Balance getCurrentBalance(Long registerId) {
        try {
            return jdbcTemplate.queryForObject(GET_CURRENT_BALANCE, new BalanceRowMapper(),
                    new Object[] { registerId });
        } catch (EmptyResultDataAccessException empty) {
            return null;
        }
    }

    @Override
    public Balance getBalanceForDate(Long registerId, LocalDate date) {
        try {
            return jdbcTemplate.queryForObject(GET_BALANCE_FOR_DATE, new BalanceRowMapper(),
                    new Object[] { registerId, date });
        } catch (EmptyResultDataAccessException empty) {
            return null;
        }
    }
}
