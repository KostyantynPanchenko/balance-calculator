package com.softserveinc.balance.calculator.dto;

import javax.validation.constraints.Size;

import com.softserveinc.balance.calculator.domain.Register;

public class RegisterDTO {

    private Long id;
    private Long storeId;
    @Size(min = 2, max = 50)
    private String name;
    @Size(min = 2, max = 30)
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

    public void setTimezome(String timezome) {
        this.timezone = timezome;
    }
}
