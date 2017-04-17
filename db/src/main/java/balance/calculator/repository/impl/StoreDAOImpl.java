package balance.calculator.repository.impl;

import static balance.calculator.repository.impl.namespaces.StoreNamespace.DELETE;
import static balance.calculator.repository.impl.namespaces.StoreNamespace.SELECT;
import static balance.calculator.repository.impl.namespaces.StoreNamespace.UPDATE;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import balance.calculator.domain.Store;
import balance.calculator.repository.StoreDAO;
import balance.calculator.repository.impl.mappers.StorePreparedStatementCreator;
import balance.calculator.repository.impl.mappers.StoreRowMapper;

/**
 * Implementation of <code>StoreRepository</code> interface.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 05/03/2017
 */
public class StoreDAOImpl extends AbstractDAO implements StoreDAO {
    
    public StoreDAOImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Store getStoreById(final Long id) {
        try {
            return (Store) template.queryForObject(SELECT, new Object[] {id}, new StoreRowMapper());
        } catch (EmptyResultDataAccessException empty) {
            return null;
        }
    }
    
    @Override
    public Long save(Store store) {
        return create(new StorePreparedStatementCreator(store));
    }

    @Override
    public int update(Store store) {
        return template.update(UPDATE, new Object[] {store.getName(), store.getDescription(), store.getId()});
    }

    @Override
    public int deleteById(Long id) {
        return template.update(DELETE, new Object[] {id});
    }

}
