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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((tenantId == null) ? 0 : tenantId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Store other = (Store) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (tenantId == null) {
            if (other.tenantId != null)
                return false;
        } else if (!tenantId.equals(other.tenantId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Store [id=" + id + ", tenantId=" + tenantId + ", name=" + name + ", description=" + description + "]";
    }
    
}
