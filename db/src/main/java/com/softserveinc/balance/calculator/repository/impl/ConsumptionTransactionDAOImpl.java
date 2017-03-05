package com.softserveinc.balance.calculator.repository.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.softserveinc.balance.calculator.domain.ConsumptionTransaction;
import com.softserveinc.balance.calculator.repository.ConsumptionTransactionDAO;

public class ConsumptionTransactionDAOImpl implements ConsumptionTransactionDAO {
    
    private final String INSERT = "insert into consumption_transactions(register_id, consumed_value, created_by) values(?, ?, ?)";
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int[] saveAll(List<ConsumptionTransaction> consumptions) {
        List<Object[]> batch = new LinkedList<>();
        consumptions.forEach(consumption -> batch.add(getBatchValues(consumption)));
        return jdbcTemplate.batchUpdate(INSERT, batch);
    }

    private Object[] getBatchValues(ConsumptionTransaction consumption) {
        return new Object[] {
                consumption.getRegisterId(),
                consumption.getConsumedValue(),
                consumption.getCreatedBy()
        };
    }

}
