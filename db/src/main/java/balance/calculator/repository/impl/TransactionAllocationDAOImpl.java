package balance.calculator.repository.impl;

import static balance.calculator.repository.impl.namespaces.TransactionAllocationNamespace.PARAM;
import static balance.calculator.repository.impl.namespaces.TransactionAllocationNamespace.SPROC_NAME;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Component;

import balance.calculator.repository.TransactionAllocationDAO;

@Component
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
