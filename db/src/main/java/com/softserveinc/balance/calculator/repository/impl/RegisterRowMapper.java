package com.softserveinc.balance.calculator.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.softserveinc.balance.calculator.domain.Register;

public class RegisterRowMapper implements RowMapper<Register> {

    public Register mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Register.Builder().setId(rs.getLong("id"))
                                    .setStoreId(rs.getLong("store_id"))
                                    .setName(rs.getString("name"))
                                    .setTimezone(rs.getString("timezone"))
                                    .build();
    }

}
