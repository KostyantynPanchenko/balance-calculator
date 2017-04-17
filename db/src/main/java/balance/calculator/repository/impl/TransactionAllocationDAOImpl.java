package balance.calculator.repository.impl;

import static balance.calculator.repository.impl.namespaces.TransactionAllocationNamespace.*;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import balance.calculator.repository.TransactionAllocationDAO;

public class TransactionAllocationDAOImpl extends StoredProcedure implements TransactionAllocationDAO {
    
    public TransactionAllocationDAOImpl(DataSource dataSource) {
        super(dataSource, SPROC_NAME);
        declareParameter(new SqlParameter(PARAM, Types.BIGINT));
        compile();
    }
    
    @Override
    public void registerAllocation(Long registerId) {
        execute(registerId);
    }

}
