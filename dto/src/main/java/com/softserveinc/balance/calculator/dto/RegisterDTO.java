package com.softserveinc.balance.calculator.dto;

import com.softserveinc.balance.calculator.domain.Register;

public class RegisterDTO {

    private long id;
    private long storeId;
    private String name;
    private String timezome;
    
    public RegisterDTO() { }

    public RegisterDTO(Register register) {
        this.id = register.getId();
        this.storeId = register.getStoreId();
        this.name = register.getName();
        this.timezome = register.getTimezome();
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimezome() {
        return timezome;
    }

    public void setTimezome(String timezome) {
        this.timezome = timezome;
    }
}
