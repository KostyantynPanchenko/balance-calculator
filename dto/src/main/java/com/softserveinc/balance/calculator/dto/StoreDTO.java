
package com.softserveinc.balance.calculator.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.softserveinc.balance.calculator.domain.Store;

public class StoreDTO {
    
    private Long id;
    @NotNull
    private Long tenantId;
    @Size(min = 2, max = 50)
    private String name;
    @Size(min = 2, max = 400)
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
