package com.softserveinc.balance.calculator.repository.impl.namespaces;

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
    public final static String SELECT = String.format("select %s, %s, %s, %s from %s where %s = ?", 
            StoreNamespace.ID_COLUMN_NAME,
            StoreNamespace.TENANT_ID_COLUMN_NAME,
            StoreNamespace.NAME_COLUMN_NAME,
            StoreNamespace.DESCRIPTION_COLUMN_NAME,
            StoreNamespace.TABLE_NAME,
            StoreNamespace.ID_COLUMN_NAME);;
    public final static String INSERT = String.format("insert into %s(%s, %s, %s) values(?, ?, ?)",
            StoreNamespace.TABLE_NAME,
            StoreNamespace.TENANT_ID_COLUMN_NAME,
            StoreNamespace.NAME_COLUMN_NAME,
            StoreNamespace.DESCRIPTION_COLUMN_NAME);
    public final static String UPDATE = String.format("update %s set %s = ?, %s = ? where %s = ?",
            StoreNamespace.TABLE_NAME,
            StoreNamespace.NAME_COLUMN_NAME,
            StoreNamespace.DESCRIPTION_COLUMN_NAME,
            StoreNamespace.ID_COLUMN_NAME);
    public final static String DELETE = String.format("delete from %s where %s = ?", 
            StoreNamespace.TABLE_NAME,
            StoreNamespace.ID_COLUMN_NAME);

}
