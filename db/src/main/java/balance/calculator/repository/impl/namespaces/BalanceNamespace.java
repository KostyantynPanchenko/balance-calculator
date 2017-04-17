package balance.calculator.repository.impl.namespaces;

/**
 * <code>BAlance</code> domain class fields to database table column names mapper.
 * 
 * @author Kostyantyn Panchenko
 */
public class BalanceNamespace {

    public final static String TABLE_NAME = "balance";
    public final static String ID_COLUMN_NAME = "id";
    public final static String REGISTER_ID_COLUMN_NAME = "register_id";
    public final static String CREATED_ON_COLUMN_NAME = "created_on";
    public final static String CREATED_BY_COLUMN_NAME = "created_by";
    public final static String TOTAL_ALLOCATED_CONSUMPTION_AMOUNT = "total_allocated_contribution_amount";
    public final static String TOTAL_ALLOCATED_CONTRIBUTION_AMOUNT = "total_allocated_consumption_amount";
    public final static String TOTAL_UNALLOCATED_CONSUMPTION_AMOUNT = "total_unallocated_consumption_amount";
    public final static String TOTAL_UNALLOCATED_CONTRIBUTION_AMOUNT = "total_unallocated_contribution_amount";
    public final static String GET_CURRENT_BALANCE = String.format("select * from %s where %s = ? order by %s desc limit 1",
            BalanceNamespace.TABLE_NAME, BalanceNamespace.REGISTER_ID_COLUMN_NAME, BalanceNamespace.ID_COLUMN_NAME);
    public final static String GET_BALANCE_FOR_DATE = String.format("select * from %s where %s = ? and CAST(%s as DATE) = ? order by %s desc limit 1",
            BalanceNamespace.TABLE_NAME, BalanceNamespace.REGISTER_ID_COLUMN_NAME,
            BalanceNamespace.CREATED_ON_COLUMN_NAME, BalanceNamespace.ID_COLUMN_NAME);
    
}
