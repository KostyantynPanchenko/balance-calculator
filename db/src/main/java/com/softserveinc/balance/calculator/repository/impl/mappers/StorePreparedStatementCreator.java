package com.softserveinc.balance.calculator.repository.impl.mappers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.softserveinc.balance.calculator.domain.Store;

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
        final String INSERT = String.format("insert into %s(%s, %s, %s) values(?, ?, ?)",
                StoreNamespace.TABLE_NAME,
                StoreNamespace.TENANT_ID_COLUMN_NAME,
                StoreNamespace.NAME_COLUMN_NAME,
                StoreNamespace.DESCRIPTION_COLUMN_NAME); 
        PreparedStatement ps = connection.prepareStatement(INSERT, new String[] {StoreNamespace.ID_COLUMN_NAME});
        ps.setLong(1, store.getTenantId());
        ps.setString(2, store.getName());
        ps.setString(3, store.getDescription());
        return ps;
    }

}
