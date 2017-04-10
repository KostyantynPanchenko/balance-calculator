package com.softserveinc.balance.calculator.repository.impl.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.softserveinc.balance.calculator.domain.Store;
import static com.softserveinc.balance.calculator.repository.impl.namespaces.StoreNamespace.*;

/**
 * RowMapper implementation for <code>Store</code> domain class.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 03/06/2017
 */
public class StoreRowMapper implements RowMapper<Store> {

    @Override
    public Store mapRow(ResultSet rs, int numRows) throws SQLException {
        Store store = new Store();
        store.setId(rs.getLong(ID_COLUMN_NAME));
        store.setTenantId(rs.getLong(TENANT_ID_COLUMN_NAME));
        store.setName(rs.getString(NAME_COLUMN_NAME));
        store.setDescription(rs.getString(DESCRIPTION_COLUMN_NAME));
        return store;
    }

}
