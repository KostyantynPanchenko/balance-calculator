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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
        result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
        result = prime * result + ((orderGrantedValue == null) ? 0 : orderGrantedValue.hashCode());
        result = prime * result + (int) (registerId ^ (registerId >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ContributionTransaction other = (ContributionTransaction) obj;
        if (createdBy == null) {
            if (other.createdBy != null)
                return false;
        } else if (!createdBy.equals(other.createdBy))
            return false;
        if (createdOn == null) {
            if (other.createdOn != null)
                return false;
        } else if (!createdOn.equals(other.createdOn))
            return false;
        if (orderGrantedValue == null) {
            if (other.orderGrantedValue != null)
                return false;
        } else if (!orderGrantedValue.equals(other.orderGrantedValue))
            return false;
        if (registerId != other.registerId)
            return false;
        return true;
    }
    
}
