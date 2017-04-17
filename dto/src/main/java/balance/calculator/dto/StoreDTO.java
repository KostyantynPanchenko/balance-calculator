
package balance.calculator.dto;

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
    
    public static class Builder {
        private Long id;
        private Long tenantId;
        private String name;
        private String description;
                
        public Builder setId(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder setTenantId(Long id) {
            this.tenantId = id;
            return this;
        }
        
        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        
        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }
        
        public StoreDTO build() {
            StoreDTO store = new StoreDTO();
            store.setId(id);
            store.setTenantId(tenantId);
            store.setName(name);
            store.setDescription(description);
            return store;
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
