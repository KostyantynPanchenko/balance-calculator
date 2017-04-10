package com.softserveinc.balance.calculator.repository.impl.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import org.springframework.jdbc.core.RowMapper;

import com.softserveinc.balance.calculator.domain.Balance;
import static com.softserveinc.balance.calculator.repository.impl.namespaces.BalanceNamespace.*;

/**
 * RowMapper implementation for <code>Store</code> domain class.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.1
 * @since 10/04/2017
 */
public class BalanceRowMapper implements RowMapper<Balance> {

    @Override
    public Balance mapRow(ResultSet rs, int numRows) throws SQLException {
        Balance balance = new Balance();
        balance.setId(rs.getLong(ID_COLUMN_NAME));
        balance.setRegisterId(rs.getLong(REGISTER_ID_COLUMN_NAME));
        balance.setCreatedBy(rs.getString(CREATED_BY_COLUMN_NAME));        
        balance.setCreatedOn(OffsetDateTime.ofInstant(rs.getTimestamp(CREATED_ON_COLUMN_NAME).toInstant(),ZoneId.systemDefault()));
        balance.setTotalAllocatedConsumptionAmount(rs.getBigDecimal(TOTAL_ALLOCATED_CONSUMPTION_AMOUNT));
        balance.setTotalAllocatedContributionAmount(rs.getBigDecimal(TOTAL_ALLOCATED_CONTRIBUTION_AMOUNT));
        balance.setTotalUnallocatedConsumptionAmount(rs.getBigDecimal(TOTAL_UNALLOCATED_CONSUMPTION_AMOUNT));
        balance.setTotalUnallocatedContributionAmount(rs.getBigDecimal(TOTAL_UNALLOCATED_CONTRIBUTION_AMOUNT));
        return balance;
    }

}
