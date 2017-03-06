package com.softserveinc.balance.calculator.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.softserveinc.balance.calculator.domain.Register;
import com.softserveinc.balance.calculator.repository.AbstractDAO;
import com.softserveinc.balance.calculator.repository.RegisterDAO;
import com.softserveinc.balance.calculator.repository.exception.DataIntegrityViolationRepositoryException;
import com.softserveinc.balance.calculator.repository.exception.DomainEntityNotFoundException;
import com.softserveinc.balance.calculator.repository.exception.RepositoryException;
import com.softserveinc.balance.calculator.repository.impl.mappers.RegisterPreparedStatementCreator;
import com.softserveinc.balance.calculator.repository.impl.mappers.RegisterRowMapper;

/**
 * Implementation of <code>RegisterRepository</code> interface.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 05/03/2017
 */
public class RegisterDAOImpl extends AbstractDAO<Register> implements RegisterDAO {

    private final static Logger LOGGER = LoggerFactory.getLogger(RegisterDAOImpl.class);
    private final static RegisterRowMapper MAPPER = new RegisterRowMapper();
    private final String GET_BY_ID = "select id, store_id, name, timezone from registers where store_id = ? and id = ?";
    private final String UPDATE = "update registers set name = ?, timezone = ? where store_id = ? and id = ?";
    private final String DELETE = "delete from registers where id = ? and store_id = ?";

    public RegisterDAOImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Register getRegisterById(Long registerId, Long storeId) throws RepositoryException {
        try {
            System.out.println("storeId=" + storeId.toString() + ", registerId=" + registerId.toString());
            return getById(GET_BY_ID, new Object[] {storeId, registerId}, MAPPER);
        } catch (EmptyResultDataAccessException notFound) {
            LOGGER.error(notFound.getMessage(), notFound);
            throw new DomainEntityNotFoundException();
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public Long insert(Register register) throws RepositoryException {
        try {
            return create(new RegisterPreparedStatementCreator(register));
        } catch (DataIntegrityViolationException violation) {
            LOGGER.error(violation.getMessage(), violation);
            throw new DataIntegrityViolationRepositoryException(violation.getMessage());
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public int update(Register register) throws RepositoryException {
        try {
            return execute(UPDATE, new Object[] { register.getName(), register.getTimezone(), register.getId() });
        } catch (DataIntegrityViolationException violation) {
            LOGGER.error(violation.getMessage(), violation);
            throw new DataIntegrityViolationRepositoryException(violation.getMessage());
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public int delete(Long storeId, Long registerId) throws RepositoryException {
        try {
            return execute(DELETE, new Object[] { registerId, storeId });
        } catch (DataIntegrityViolationException violation) {
            LOGGER.error(violation.getMessage(), violation);
            throw new DataIntegrityViolationRepositoryException(violation.getMessage());
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RepositoryException(e.getMessage());
        }
    }

}
