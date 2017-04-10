package com.softserveinc.balance.calculator.repository.impl;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.softserveinc.balance.calculator.repository.TransactionAllocationDAO;
import static com.softserveinc.balance.calculator.repository.impl.namespaces.TransactionAllocationNamespace.*;

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
