package com.softserveinc.balance.calculator.repository.impl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.softserveinc.balance.calculator.domain.Register;
import com.softserveinc.balance.calculator.repository.RegisterDAO;

public class RegisterDAOImpl implements RegisterDAO {

    private final String GET_BY_ID = "select id, store_id, name, timezone from registers where id = ? and store_id = ?";
    private final String INSERT = "insert into registers(store_id, name, timezone) values(?, ?, ?) ";
    private final String UPDATE = "update registers set name = ?, timezone = ? where id = ? and store_id = ?";
    private final String DELETE = "delete from registers where id = ?";
    private JdbcTemplate template;
    
    public RegisterDAOImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }
    
    public Register getRegisterById(Long storeId, Long registerId) {
        // TODO Auto-generated method stub
        return null;
    }

    public int save(Register register) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int update(Register register, Long storeId, Long registerId) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int delete(Long storeId, Long registerId) {
        // TODO Auto-generated method stub
        return 0;
    }

}
