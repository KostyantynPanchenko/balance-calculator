package com.softserveinc.balance.calculator.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

public abstract class AbstractTransactionDTO {

    protected Long id;
    @NotNull
    protected Long registerId;
    protected Timestamp createdOn;
    protected String createdBy;
    
    protected AbstractTransactionDTO() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Long registerId) {
        this.registerId = registerId;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
}
