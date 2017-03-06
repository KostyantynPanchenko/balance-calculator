package com.softserveinc.balance.calculator.repository.impl.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.softserveinc.balance.calculator.domain.Store;

/**
 * RowMapper implementation for <code>Store</code> domain class.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 03/06/2017
 */
public class StoreRowMapper implements RowMapper<Store> {

    public Store mapRow(ResultSet rs, int numRows) throws SQLException {
        Store store = new Store();
        store.setId(rs.getLong(StoreNamespace.ID_COLUMN_NAME));
        store.setTenantId(rs.getLong(StoreNamespace.TENANT_ID_COLUMN_NAME));
        store.setName(rs.getString(StoreNamespace.NAME_COLUMN_NAME));
        store.setDescription(rs.getString(StoreNamespace.DESCRIPTION_COLUMN_NAME));
        return store;
    }

}
