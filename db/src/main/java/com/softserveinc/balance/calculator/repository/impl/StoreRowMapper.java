package com.softserveinc.balance.calculator.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.softserveinc.balance.calculator.domain.Store;

public class StoreRowMapper implements RowMapper<Store> {

    public Store mapRow(ResultSet rs, int numRows) throws SQLException {
        Store store = new Store();
        store.setId(rs.getLong("id"));
        store.setTenantId(rs.getLong("tenant_id"));
        store.setName(rs.getString("name"));
        store.setDescription(rs.getString("description"));
        return store;
    }

}
