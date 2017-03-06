package com.softserveinc.balance.calculator.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ContributionTransaction extends Transaction {

    private BigDecimal orderGrantedValue;
    
    public ContributionTransaction() { }
    
    public static class Builder {
        private ContributionTransaction transaction;
        
        public Builder() {
            transaction = new ContributionTransaction();
        }
        
        public Builder setId(Long id) {
            transaction.setId(id);
            return this;
        }
        
        public Builder setRegisterId(Long registerId) {
            transaction.setRegisterId(registerId);
            return this;
        }
        
        public Builder setOrderGrantedValue(BigDecimal orderGrantedValue) {
            transaction.setOrderGrantedValue(orderGrantedValue);
            return this;
        }
        
        public Builder setCreatedOn(Timestamp createdOn) {
            transaction.setCreatedOn(createdOn);
            return this;
        }
        
        public Builder setCreatedBy(String createdBy) {
            transaction.setCreatedBy(createdBy);
            return this;
        }
        
        public ContributionTransaction build() {
            return transaction;
        }
    }

    public BigDecimal getOrderGrantedValue() {
        return orderGrantedValue;
    }

    public void setOrderGrantedValue(BigDecimal orderGrantedValue) {
        this.orderGrantedValue = orderGrantedValue;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((createdBy == null) ? 0 : createdBy.hashCode());
        result = prime * result
                + ((createdOn == null) ? 0 : createdOn.hashCode());
        result = prime * result + ((orderGrantedValue == null) ? 0
                : orderGrantedValue.hashCode());
        result = prime * result
                + ((registerId == null) ? 0 : registerId.hashCode());
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
        if (registerId == null) {
            if (other.registerId != null)
                return false;
        } else if (!registerId.equals(other.registerId))
            return false;
        return true;
    }
    
}
