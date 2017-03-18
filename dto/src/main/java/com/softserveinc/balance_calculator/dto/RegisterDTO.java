package com.softserveinc.balance_calculator.dto;

import javax.validation.constraints.Size;

import com.softserveinc.balance_calculator.dto.validator.ValidTimezone;

@ValidTimezone
public class RegisterDTO {

    private Long id;
    private Long storeId;
    @Size(min = 2, max = 50)
    private String name;
    private String timezone;
    
    public RegisterDTO() { }
    
    public static class Builder {
        private RegisterDTO register;
        
        public Builder() {
            this.register = new RegisterDTO();
        }
        public Builder setId(Long id) {
            this.register.setId(id);
            return this;
        }
        
        public Builder setStoreId(Long storeId) {
            this.register.setStoreId(storeId);
            return this;
        }
        
        public Builder setName(String name) {
            this.register.setName(name);
            return this;
        }
        
        public Builder setTimezone(String timezone) {
            this.register.setTimezone(timezone);
            return this;
        }
        
        public RegisterDTO build() {
            return this.register;
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
