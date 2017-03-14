package com.softserveinc.balance_calculator.repository.impl.namespaces;

/**
 * <code>Register</code> domain class fields to database table column names mapper.
 * 
 * @author Kostyantyn Panchenko
 */
public class RegisterNamespace {

    public final static String TABLE_NAME = "registers";
    public final static String ID_COLUMN_NAME = "id";
    public final static String STORE_ID_COLUMN_NAME = "store_id";
    public final static String NAME_COLUMN_NAME = "name";
    public final static String TIMEZONE_COLUMN_NAME = "timezone";
    public final static String SELECT = "select %s, %s, %s, %s from %s where %s = ?";
    public final static String INSERT = "insert into %s(%s, %s, %s) values(?, ?, ?)";
    public final static String UPDATE = "update %s set %s = ?, %s = ?, %s = ? where %s = ?";
    public final static String DELETE = "delete from %s where %s = ?";
    
    
}
