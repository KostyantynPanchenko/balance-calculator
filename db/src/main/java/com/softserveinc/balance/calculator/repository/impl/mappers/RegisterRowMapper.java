package com.softserveinc.balance.calculator.repository.impl.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.softserveinc.balance.calculator.domain.Register;
import static com.softserveinc.balance.calculator.repository.impl.namespaces.RegisterNamespace.*;

/**
 * RowMapper implementation for <code>Register</code> domain class.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 03/06/2017
 */
public class RegisterRowMapper implements RowMapper<Register> {

    @Override
    public Register mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Register.Builder().setId(rs.getLong(ID_COLUMN_NAME))
                                    .setStoreId(rs.getLong(STORE_ID_COLUMN_NAME))
                                    .setName(rs.getString(NAME_COLUMN_NAME))
                                    .setTimezone(rs.getString(TIMEZONE_COLUMN_NAME))
                                    .build();
    }

}
