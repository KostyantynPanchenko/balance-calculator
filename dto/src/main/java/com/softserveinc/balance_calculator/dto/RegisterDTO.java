package com.softserveinc.balance_calculator.dto;

import javax.validation.constraints.Size;

import com.softserveinc.balance_calculator.domain.Register;
import com.softserveinc.balance_calculator.dto.validator.ValidTimezone;

@ValidTimezone
public class RegisterDTO {

    private Long id;
    private Long storeId;
    @Size(min = 2, max = 50)
    private String name;
    private String timezone;
    
    public RegisterDTO() { }

    public RegisterDTO(Register register) {
        this.id = register.getId();
        this.storeId = register.getStoreId();
        this.name = register.getName();
        this.timezone = register.getTimezone();
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
