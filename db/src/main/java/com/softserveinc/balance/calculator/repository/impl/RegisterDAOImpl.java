package com.softserveinc.balance.calculator.repository.impl;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.softserveinc.balance.calculator.domain.Register;
import com.softserveinc.balance.calculator.repository.RegisterDAO;

public class RegisterDAOImpl implements RegisterDAO {

    private final String GET_BY_ID = "select id, store_id, name, timezone from registers where id = ?";
    private final String INSERT = "insert into registers(store_id, name, timezone) values(?, ?, ?)";
    private final String UPDATE = "update registers set name = ?, timezone = ? where id = ?";
    private final String DELETE = "delete from registers where id = ? and store_id = ?";
    private JdbcTemplate template;
    
    public void setDataSource(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }
    
    public Register getRegisterById(Long registerId) {
        Register register = null;
        try {
            register = (Register) template.queryForObject(GET_BY_ID, new Object[]{registerId}, new RegisterRowMapper());
        } catch (EmptyResultDataAccessException e) {
            // nothing we can do except log it
        }
        return register;
    }

    public int save(Register register) {
        int status = 0;
        try {
            return template.update(INSERT, new Object[] {register.getStoreId(), register.getName(), register.getTimezone()});
        } catch (DataAccessException e) {
            
        }
        return status;
    }

    public int update(Register register, Long registerId) {
        return template.update(UPDATE, new Object[] {register.getName(), register.getTimezone(), registerId});
    }

    public int delete(Long storeId, Long registerId) {
        System.out.println("DAO");
        return template.update(DELETE, new Object[] {registerId, storeId});
    }

}
