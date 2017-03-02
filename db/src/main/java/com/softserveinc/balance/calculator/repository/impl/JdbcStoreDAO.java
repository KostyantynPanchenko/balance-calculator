package com.softserveinc.balance.calculator.repository.impl;

import com.softserveinc.balance.calculator.domain.Store;
import com.softserveinc.balance.calculator.repository.StoreDAO;

public class JdbcStoreDAO implements StoreDAO {
    
//    private JdbcTemplate template;
//    
//    public void setDataSource(DataSource dataSource) {
//        this.template = new JdbcTemplate(dataSource);
//    }
    
    public void save(Store store) {
    }

    public Store getStoreById(final Long id) {
//        String SQL = "select tenant_id, name, description from store where id = ?";
//        Store store = (Store) template.queryForObject(SQL, new Object[]{id}, new StoreRowMapper());
//        return store;
        Store store = new Store();
        store.setId(id);
        store.setTenantId(100500L);
        store.setName("StoreName");
        store.setDescription("theDescription");
        return store;
    }

    public void update(Store store) {
        // TODO Auto-generated method stub
    }

    public void deleteById(long id) {
        // TODO Auto-generated method stub
    }

}

