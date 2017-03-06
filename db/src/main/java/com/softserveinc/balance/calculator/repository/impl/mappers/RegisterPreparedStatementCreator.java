package com.softserveinc.balance.calculator.repository.impl.mappers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.softserveinc.balance.calculator.domain.Register;

/**
 * PreparedStatementCreator implementation for <code>Register</code> domain class.
 * 
 * @author Kostyantyn Panchenko
 */
public class RegisterPreparedStatementCreator implements PreparedStatementCreator {

    private Register register;
    
    public RegisterPreparedStatementCreator(Register register) {
        this.register = register;
    }
    
    @Override
    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        final String INSERT = String.format("insert into %s(%s, %s, %s) values(?, ?, ?)",
                RegisterNamespace.TABLE_NAME,
                RegisterNamespace.STORE_ID_COLUMN_NAME,
                RegisterNamespace.NAME_COLUMN_NAME,
                RegisterNamespace.TIMEZONE_COLUMN_NAME);
        PreparedStatement ps = connection.prepareStatement(INSERT, new String[] {RegisterNamespace.ID_COLUMN_NAME});
        ps.setLong(1, register.getStoreId());
        ps.setString(2, register.getName());
        ps.setString(3, register.getTimezone());
        return ps;
    }

}
