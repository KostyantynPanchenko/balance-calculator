package com.softserveinc.balance_calculator.repository.impl.namespaces;

/**
 * <code>Store</code> entity fields to database table column names mapper class.
 * 
 * @author Kostyantyn Panchenko
 */
public class StoreNamespace {
    
    public final static String TABLE_NAME = "stores";
    public final static String ID_COLUMN_NAME = "id";
    public final static String TENANT_ID_COLUMN_NAME = "tenant_id";
    public final static String NAME_COLUMN_NAME = "name";
    public final static String DESCRIPTION_COLUMN_NAME = "description";
    public final static String SELECT = "select %s, %s, %s, %s from %s where %s = ?";
    public final static String INSERT = "insert into %s(%s, %s, %s) values(?, ?, ?)";
    public final static String UPDATE = "update %s set %s = ?, %s = ?, %s = ? where %s = ?";
    public final static String DELETE = "delete from %s where %s = ?";

}
