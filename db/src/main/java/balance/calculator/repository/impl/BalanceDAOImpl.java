package balance.calculator.repository.impl;

import static balance.calculator.repository.impl.namespaces.BalanceNamespace.GET_BALANCE_FOR_DATE;
import static balance.calculator.repository.impl.namespaces.BalanceNamespace.GET_CURRENT_BALANCE;

import java.time.LocalDate;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import balance.calculator.domain.Balance;
import balance.calculator.repository.BalanceDAO;
import balance.calculator.repository.impl.mappers.BalanceRowMapper;

@Component
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
