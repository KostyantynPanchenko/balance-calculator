package com.softserveinc.balance_calculator.repository.impl;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.softserveinc.balance_calculator.repository.TransactionAllocationDAO;
import com.softserveinc.balance_calculator.repository.impl.namespaces.TransactionAllocationNamespace;

public class TransactionAllocationDAOImpl extends StoredProcedure implements TransactionAllocationDAO {
    
    public TransactionAllocationDAOImpl(DataSource dataSource) {
        super(dataSource, TransactionAllocationNamespace.SPROC_NAME);
        declareParameter(new SqlParameter(TransactionAllocationNamespace.PARAM, Types.BIGINT));
        compile();
    }
    
    @Override
    public void registerAllocation(Long registerId) {
        execute(registerId);
    }

}
