package com.softserveinc.balance.calculator.repository.impl.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.softserveinc.balance.calculator.domain.Balance;
import com.softserveinc.balance.calculator.repository.impl.namespaces.BalanceNamespace;

/**
 * RowMapper implementation for <code>Store</code> domain class.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 03/06/2017
 */
public class BalanceRowMapper implements RowMapper<Balance> {

    @Override
    public Balance mapRow(ResultSet rs, int numRows) throws SQLException {
        Balance balance = new Balance();
        balance.setId(rs.getLong(BalanceNamespace.ID_COLUMN_NAME));
        balance.setRegisterId(rs.getLong(BalanceNamespace.REGISTER_ID_COLUMN_NAME));
        balance.setCreatedBy(rs.getString(BalanceNamespace.CREATED_BY_COLUMN_NAME));
        balance.setCreatedOn(rs.getTimestamp(BalanceNamespace.CREATED_ON_COLUMN_NAME));
        balance.setTotalAllocatedConsumptionAmount(rs.getBigDecimal(BalanceNamespace.TOTAL_ALLOCATED_CONSUMPTION_AMOUNT));
        balance.setTotalAllocatedContributionAmount(rs.getBigDecimal(BalanceNamespace.TOTAL_ALLOCATED_CONTRIBUTION_AMOUNT));
        balance.setTotalUnallocatedConsumptionAmount(rs.getBigDecimal(BalanceNamespace.TOTAL_UNALLOCATED_CONSUMPTION_AMOUNT));
        balance.setTotalUnallocatedContributionAmount(rs.getBigDecimal(BalanceNamespace.TOTAL_UNALLOCATED_CONTRIBUTION_AMOUNT));
        return balance;
    }

}
