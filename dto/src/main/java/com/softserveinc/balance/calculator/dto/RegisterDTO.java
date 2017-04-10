package com.softserveinc.balance.calculator.dto;

import javax.validation.constraints.Size;

import com.softserveinc.balance.calculator.dto.validator.ValidTimezone;

@ValidTimezone
public class RegisterDTO {

    private Long id;
    private Long storeId;
    @Size(min = 2, max = 50)
    private String name;
    private String timezone;
    
    public static class Builder {
        private Long id;
        private Long storeId;
        private String name;
        private String timezone;
        
        public Builder setId(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder setStoreId(Long storeId) {
            this.storeId = storeId;
            return this;
        }
        
        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        
        public Builder setTimezone(String timezone) {
            this.timezone = timezone;
            return this;
        }
        
        public RegisterDTO build() {
            RegisterDTO register = new RegisterDTO();
            register.setId(id);
            register.setStoreId(storeId);
            register.setName(name);
            register.setTimezone(timezone);
            return register;
        }
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @Override
    public String toString() {
        return "RegisterDTO [id=" + id + ", storeId=" + storeId + ", name=" + name + ", timezone=" + timezone + "]";
    }
    
}
