package com.softserveinc.balance_calculator.repository.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.softserveinc.balance_calculator.domain.Register;
import com.softserveinc.balance_calculator.repository.AbstractDAO;
import com.softserveinc.balance_calculator.repository.RegisterDAO;
import com.softserveinc.balance_calculator.repository.exception.DataIntegrityViolationRepositoryException;
import com.softserveinc.balance_calculator.repository.exception.DomainEntityNotFoundException;
import com.softserveinc.balance_calculator.repository.exception.RepositoryException;
import com.softserveinc.balance_calculator.repository.impl.mappers.RegisterPreparedStatementCreator;
import com.softserveinc.balance_calculator.repository.impl.mappers.RegisterRowMapper;
import com.softserveinc.balance_calculator.repository.impl.namespaces.RegisterNamespace;

/**
 * Implementation of <code>RegisterRepository</code> interface.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 05/03/2017
 */
public class RegisterDAOImpl extends AbstractDAO<Register> implements RegisterDAO {

    public RegisterDAOImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Register getRegisterById(Long registerId) throws DomainEntityNotFoundException, RepositoryException {
        try {
            String SQL = String.format(RegisterNamespace.SELECT,
                    RegisterNamespace.ID_COLUMN_NAME, RegisterNamespace.STORE_ID_COLUMN_NAME,
                    RegisterNamespace.NAME_COLUMN_NAME, RegisterNamespace.TIMEZONE_COLUMN_NAME,
                    RegisterNamespace.TABLE_NAME, RegisterNamespace.ID_COLUMN_NAME);
            return getById(SQL, new Object[] {registerId}, new RegisterRowMapper());
        } catch (EmptyResultDataAccessException notFound) {
            throw new DomainEntityNotFoundException(notFound);
        } catch (DataAccessException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public Long insert(Register register) throws DataIntegrityViolationRepositoryException, RepositoryException {
        try {
            return create(new RegisterPreparedStatementCreator(register));
        } catch (DataIntegrityViolationException violation) {
            throw new DataIntegrityViolationRepositoryException(violation);
        } catch (DataAccessException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public int update(Register register) throws DataIntegrityViolationRepositoryException, RepositoryException {
        try {
            String SQL = String.format(RegisterNamespace.UPDATE, RegisterNamespace.TABLE_NAME,
                    RegisterNamespace.STORE_ID_COLUMN_NAME, RegisterNamespace.NAME_COLUMN_NAME,
                    RegisterNamespace.TIMEZONE_COLUMN_NAME, RegisterNamespace.ID_COLUMN_NAME);
            return execute(SQL, new Object[] { register.getStoreId(), register.getName(), register.getTimezone(), register.getId() });
        } catch (DataIntegrityViolationException violation) {
            throw new DataIntegrityViolationRepositoryException(violation);
        } catch (DataAccessException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public int delete(Long registerId) throws DataIntegrityViolationRepositoryException, RepositoryException {
        try {
            String SQL = String.format(RegisterNamespace.DELETE,
                    RegisterNamespace.TABLE_NAME, RegisterNamespace.ID_COLUMN_NAME);
            return execute(SQL, new Object[] { registerId});
        } catch (DataIntegrityViolationException violation) {
            throw new DataIntegrityViolationRepositoryException(violation);
        } catch (DataAccessException e) {
            throw new RepositoryException(e);
        }
    }

}
