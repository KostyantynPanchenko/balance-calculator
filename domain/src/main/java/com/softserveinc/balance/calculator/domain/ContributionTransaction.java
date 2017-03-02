package com.softserveinc.balance.calculator.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ContributionTransaction {

    private long id;
    private long registerId;
    private BigDecimal orderGrantedValue;
    private Timestamp createdOn;
    private String createdBy;
    
    public ContributionTransaction() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRegisterId() {
        return registerId;
    }

    public void setRegisterId(long registerId) {
        this.registerId = registerId;
    }

    public BigDecimal getOrderGrantedValue() {
        return orderGrantedValue;
    }

    public void setOrderGrantedValue(BigDecimal orderGrantedValue) {
        this.orderGrantedValue = orderGrantedValue;
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
