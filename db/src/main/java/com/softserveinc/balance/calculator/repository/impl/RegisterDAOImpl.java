package com.softserveinc.balance.calculator.repository.impl;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.softserveinc.balance.calculator.domain.Register;
import com.softserveinc.balance.calculator.repository.RegisterDAO;
import com.softserveinc.balance.calculator.repository.exception.DataIntegrityViolationRepositoryException;
import com.softserveinc.balance.calculator.repository.exception.DomainEntityNotFoundException;
import com.softserveinc.balance.calculator.repository.exception.RepositoryException;

public class RegisterDAOImpl implements RegisterDAO {

    private final String GET_BY_ID = "select id, store_id, name, timezone from registers where id = ?";
    private final String INSERT = "insert into registers(store_id, name, timezone) values(?, ?, ?)";
    private final String UPDATE = "update registers set name = ?, timezone = ? where id = ?";
    private final String DELETE = "delete from registers where id = ? and store_id = ?";
    private JdbcTemplate template;
    
    public void setDataSource(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }
    
    public Register getRegisterById(Long registerId) throws RepositoryException {
        Register register = null;
        try {
            register = (Register) template.queryForObject(GET_BY_ID, new Object[]{registerId}, new RegisterRowMapper());
        } catch (EmptyResultDataAccessException empty) {
            throw new DomainEntityNotFoundException();
        } catch (DataAccessException e) {
            throw new RepositoryException(e.getMessage());
        }
        return register;
    }

    public int save(Register register) throws RepositoryException {
        return execute(INSERT, new Object[] {register.getStoreId(), register.getName(), register.getTimezone()});
    }

    public int update(Register register, Long registerId) throws RepositoryException {
        return execute(UPDATE, new Object[] {register.getName(), register.getTimezone(), registerId});
    }

    public int delete(Long storeId, Long registerId) throws RepositoryException {
        return execute(DELETE, new Object[] {registerId, storeId});
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
