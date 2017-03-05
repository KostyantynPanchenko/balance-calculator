package com.softserveinc.balance.calculator.repository.impl;

import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.softserveinc.balance.calculator.domain.Register;
import com.softserveinc.balance.calculator.repository.RegisterDAO;
import com.softserveinc.balance.calculator.repository.exception.DataIntegrityViolationRepositoryException;
import com.softserveinc.balance.calculator.repository.exception.DomainEntityNotFoundException;
import com.softserveinc.balance.calculator.repository.exception.RepositoryException;
import com.softserveinc.balance.calculator.repository.impl.mappers.RegisterRowMapper;

public class RegisterDAOImpl implements RegisterDAO {

    private final String GET = "select id, store_id, name, timezone from registers where store_id = ? and id = ?";
    private final String INSERT = "insert into registers(store_id, name, timezone) values(?, ?, ?)";
    private final String UPDATE = "update registers set name = ?, timezone = ? where store_id = ? and id = ?";
    private final String DELETE = "delete from registers where id = ? and store_id = ?";
    private JdbcTemplate template;

    public void setDataSource(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    public Register getRegisterById(Long registerId, Long storeId) throws RepositoryException {
        try {
            return (Register) template.queryForObject(GET,
                    new Object[] { storeId, registerId },
                    new RegisterRowMapper());
        } catch (EmptyResultDataAccessException empty) {
            throw new DomainEntityNotFoundException();
        } catch (DataAccessException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    public Long save(Register register) throws RepositoryException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT, new String[] { "id" });
            ps.setLong(1, register.getStoreId());
            ps.setString(2, register.getName());
            ps.setString(3, register.getTimezone());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public int update(Register register) throws RepositoryException {
        return execute(UPDATE, new Object[] { register.getName(), register.getTimezone(), register.getId() });
    }

    public int delete(Long storeId, Long registerId) throws RepositoryException {
        return execute(DELETE, new Object[] { registerId, storeId });
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
