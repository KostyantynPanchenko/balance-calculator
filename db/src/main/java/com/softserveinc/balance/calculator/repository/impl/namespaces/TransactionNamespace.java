package com.softserveinc.balance.calculator.repository.impl.namespaces;

/**
 * Consumption transaction and contribution transaction tables columns names definitions.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 06/03/2017
 */
public class TransactionNamespace {

    public final static String CONSUMPTION_TABLE_NAME = "consumption_transactions";
    public final static String CONTRIBUTION_TABLE_NAME = "contribution_transactions";
    public final static String ID_COLUMN_NAME = "id";
    public final static String REGISTER_ID_COLUMN_NAME = "register_id";
    public final static String CREATED_BY_COLUMN_NAME = "created_by";
    public final static String CREATED_ON_COLUMN_NAME = "created_on";
    public final static String CONSUMED_VALUE_COLUMN_NAME = "consumed_value";
    public final static String ORDER_GRANTED_VALUE_COLUMN_NAME = "order_granted_value";
    public final static String CONSUMPTION_BATCH = "insert into %s(%s, %s, %s) values(?, ?, ?";
    public final static String CONTRIBUTION_BATCH = "insert into %s(%s, %s, %s) values(?, ?, ?)";
    
}
