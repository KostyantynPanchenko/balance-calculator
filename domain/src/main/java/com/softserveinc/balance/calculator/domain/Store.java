package com.softserveinc.balance.calculator.domain;

public class Store {

    private Long id;
    private Long tenantId;
    private String name;
    private String description;
    
    public Store() { }

    public static class Builder {
        private Store store;
        
        public Builder() {
            this.store = new Store();
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
        
        public Store build() {
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
}
