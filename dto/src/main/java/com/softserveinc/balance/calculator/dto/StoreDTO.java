
package com.softserveinc.balance.calculator.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    
    public static class Builder {
        private StoreDTO store;
        
        public Builder() {
            this.store = new StoreDTO();
        }
        
        public Builder setId(Long id) {
            this.store.setId(id);
            return this;
        }
        
        public Builder setTenantId(Long id) {
            this.store.setTenantId(id);
            return this;
        }
        
        public Builder setName(String name) {
            this.store.setName(name);
            return this;
        }
        
        public Builder setDescription(String description) {
            this.store.setDescription(description);
            return this;
        }
        
        public StoreDTO build() {
            return this.store;
        }
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

    @Override
    public String toString() {
        return "StoreDTO [id=" + id + ", tenantId=" + tenantId + ", name=" + name + ", description=" + description + "]";
    }
    
    
}
