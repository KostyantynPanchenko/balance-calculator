package com.softserveinc.balance.calculator.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ConsumptionTransaction {

    private Long id;
    private Long registerId;
    private BigDecimal consumedValue;
    private Timestamp createdOn;
    private String createdBy;
    
    public ConsumptionTransaction() { }

    public static class Builder {
        private ConsumptionTransaction transaction;
        
        public Builder() {
            transaction = new ConsumptionTransaction();
        }
        
        public Builder setId(Long id) {
            transaction.setId(id);
            return this;
        }
        
        public Builder setRegisterId(Long registerId) {
            transaction.setRegisterId(registerId);
            return this;
        }
        
        public Builder setConsumedValue(BigDecimal consumedValue) {
            transaction.setConsumedValue(consumedValue);
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
        
        public ConsumptionTransaction build() {
            return transaction;
        }
    }
    
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((consumedValue == null) ? 0 : consumedValue.hashCode());
        result = prime * result
                + ((createdBy == null) ? 0 : createdBy.hashCode());
        result = prime * result
                + ((createdOn == null) ? 0 : createdOn.hashCode());
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
        ConsumptionTransaction other = (ConsumptionTransaction) obj;
        if (consumedValue == null) {
            if (other.consumedValue != null)
                return false;
        } else if (!consumedValue.equals(other.consumedValue))
            return false;
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
        if (registerId == null) {
            if (other.registerId != null)
                return false;
        } else if (!registerId.equals(other.registerId))
            return false;
        return true;
    }
}
