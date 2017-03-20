package com.softserveinc.balance.calculator.dto;

import java.time.OffsetDateTime;

import javax.validation.constraints.Size;

public abstract class AbstractTransactionDTO {

    protected Long id;
    protected Long registerId;
    protected OffsetDateTime createdOn;
    @Size(min = 2, max = 50)
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

    public OffsetDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(OffsetDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
}
