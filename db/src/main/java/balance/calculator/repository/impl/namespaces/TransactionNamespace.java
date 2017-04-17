package balance.calculator.repository.impl.namespaces;

/**
 * <code>ConsumptionTransaction</code> and <code>ContributionTransaction</code>
 * tables columns names definitions.
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
    public final static String CONSUMPTION_BATCH = String.format("insert into %s(%s, %s, %s) values(?, ?, ?)",
            TransactionNamespace.CONSUMPTION_TABLE_NAME,
            TransactionNamespace.REGISTER_ID_COLUMN_NAME,
            TransactionNamespace.CONSUMED_VALUE_COLUMN_NAME,
            TransactionNamespace.CREATED_BY_COLUMN_NAME);
    public final static String CONTRIBUTION_BATCH = String.format("insert into %s(%s, %s, %s) values(?, ?, ?)",
            TransactionNamespace.CONTRIBUTION_TABLE_NAME,
            TransactionNamespace.REGISTER_ID_COLUMN_NAME,
            TransactionNamespace.ORDER_GRANTED_VALUE_COLUMN_NAME,
            TransactionNamespace.CREATED_BY_COLUMN_NAME);
    
}
