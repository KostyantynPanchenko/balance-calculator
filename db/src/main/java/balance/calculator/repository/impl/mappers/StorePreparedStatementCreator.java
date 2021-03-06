package balance.calculator.repository.impl.mappers;

import static balance.calculator.repository.impl.namespaces.StoreNamespace.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import balance.calculator.domain.Store;

/**
 * PreparedStatementCreator implementation for <code>Store</code> domain class.
 * 
 * @author Kostyantyn Panchenko
 */
public class StorePreparedStatementCreator implements PreparedStatementCreator {

    private final Store store;
    
    public StorePreparedStatementCreator(Store store) {
        this.store = store;
    }
    
    @Override
    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(INSERT, new String[] {ID_COLUMN_NAME});
        ps.setLong(1, store.getTenantId());
        ps.setString(2, store.getName());
        ps.setString(3, store.getDescription());
        return ps;
    }

}
