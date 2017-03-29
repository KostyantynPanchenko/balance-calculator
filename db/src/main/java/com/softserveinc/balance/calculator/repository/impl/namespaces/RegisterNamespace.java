package com.softserveinc.balance.calculator.repository.impl.namespaces;

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
    public final static String SELECT = String.format("select %s, %s, %s, %s from %s where %s = ?",
            RegisterNamespace.ID_COLUMN_NAME, RegisterNamespace.STORE_ID_COLUMN_NAME,
            RegisterNamespace.NAME_COLUMN_NAME, RegisterNamespace.TIMEZONE_COLUMN_NAME,
            RegisterNamespace.TABLE_NAME, RegisterNamespace.ID_COLUMN_NAME);
    public final static String INSERT = String.format("insert into %s(%s, %s, %s) values(?, ?, ?)",
            RegisterNamespace.TABLE_NAME,
            RegisterNamespace.STORE_ID_COLUMN_NAME,
            RegisterNamespace.NAME_COLUMN_NAME,
            RegisterNamespace.TIMEZONE_COLUMN_NAME);
    public final static String UPDATE = String.format("update %s set %s = ?, %s = ?, %s = ? where %s = ?",
            RegisterNamespace.TABLE_NAME, RegisterNamespace.STORE_ID_COLUMN_NAME,
            RegisterNamespace.NAME_COLUMN_NAME, RegisterNamespace.TIMEZONE_COLUMN_NAME,
            RegisterNamespace.ID_COLUMN_NAME);
    public final static String DELETE = String.format("delete from %s where %s = ?",
            RegisterNamespace.TABLE_NAME, RegisterNamespace.ID_COLUMN_NAME);
    
    
}
