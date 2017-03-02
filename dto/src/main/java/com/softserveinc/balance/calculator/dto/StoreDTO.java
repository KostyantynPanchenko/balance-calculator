
package com.softserveinc.balance.calculator.dto;

import com.softserveinc.balance.calculator.domain.Store;

public class StoreDTO {
    private Long id;
    private Long tenantId;
    private String name;
    private String description;

    public StoreDTO() {
    }

    public StoreDTO(Store store) {
        this.id = store.getId();
        this.tenantId = store.getTenantId();
        this.name = store.getName();
        this.description = store.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
