package com.softserveinc.balance.calculator.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.softserveinc.balance.calculator.domain.ConsumptionTransaction;

public class ConsumptionTransactionDTO {

    private Long id;
    @NotNull
    private Long registerId;
    @NotNull
    @Min(0)
    private BigDecimal consumedValue;
    private Timestamp createdOn;
    private String createdBy;
    
    public ConsumptionTransactionDTO() { }
    
    public ConsumptionTransactionDTO(ConsumptionTransaction transaction) {
        id = transaction.getId();
        registerId = transaction.getRegisterId();
        consumedValue = transaction.getConsumedValue();
        createdOn = transaction.getCreatedOn();
        createdBy = transaction.getCreatedBy();
    }

    public static class Builder {
        private ConsumptionTransactionDTO dto;
        
        public Builder() {
            dto = new ConsumptionTransactionDTO();
        }
        
        public Builder setId(Long id) {
            dto.setId(id);
            return this;
        }
        
        public Builder setRegisterId(Long registerId) {
            dto.setRegisterId(registerId);
            return this;
        }
        
        public Builder setConsumedValue(BigDecimal consumedValue) {
            dto.setConsumedValue(consumedValue);
            return this;
        }
        
        public Builder setCreatedOn(Timestamp createdOn) {
            dto.setCreatedOn(createdOn);
            return this;
        }
        
        public Builder setCreatedBy(String createdBy) {
            dto.setCreatedBy(createdBy);
            return this;
        }
        
        public ConsumptionTransactionDTO build() {
            return dto;
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
    
}
