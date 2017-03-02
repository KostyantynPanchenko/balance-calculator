package com.softserveinc.balance.calculator.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ConsumptionTransaction {

    private long id;
    private long registerId;
    private BigDecimal consumedValue;
    private Timestamp createdOn;
    private String createdBy;
    
    public ConsumptionTransaction() { }

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

    public BigDecimal getConsumedValue() {
        return consumedValue;
    }

    public void setConsumedValue(BigDecimal consumedValue) {
        this.consumedValue = consumedValue;
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
