package balance.calculator.repository.impl;

import static balance.calculator.repository.impl.namespaces.RegisterNamespace.DELETE;
import static balance.calculator.repository.impl.namespaces.RegisterNamespace.SELECT;
import static balance.calculator.repository.impl.namespaces.RegisterNamespace.UPDATE;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import balance.calculator.domain.Register;
import balance.calculator.repository.RegisterDAO;
import balance.calculator.repository.impl.mappers.RegisterPreparedStatementCreator;
import balance.calculator.repository.impl.mappers.RegisterRowMapper;

/**
 * Implementation of <code>RegisterRepository</code> interface.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 05/03/2017
 */
public class RegisterDAOImpl extends AbstractDAO implements RegisterDAO {

    public RegisterDAOImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Register getRegisterById(Long registerId) {
        try {
            return template.queryForObject(SELECT, new Object[] {registerId}, new RegisterRowMapper());
        } catch (EmptyResultDataAccessException notFound) {
            return null;
        }
    }

    @Override
    public Long insert(Register register) {
        return create(new RegisterPreparedStatementCreator(register));
    }

    @Override
    public int update(Register register) {
        return template.update(UPDATE, new Object[] { register.getStoreId(), register.getName(), register.getTimezone(), register.getId() });
    }

    @Override
    public int delete(Long registerId) {
        return template.update(DELETE, new Object[] { registerId});
    }

}
