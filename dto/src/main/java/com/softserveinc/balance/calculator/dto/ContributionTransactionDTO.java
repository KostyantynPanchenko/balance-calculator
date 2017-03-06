package com.softserveinc.balance.calculator.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.softserveinc.balance.calculator.domain.ContributionTransaction;

public class ContributionTransactionDTO extends AbstractTransactionDTO {

    @NotNull
    @Min(0)
    private BigDecimal orderGrantedValue;
    
    public ContributionTransactionDTO() { }
    
    public ContributionTransactionDTO(ContributionTransaction transaction) {
        id = transaction.getId();
        registerId = transaction.getRegisterId();
        orderGrantedValue = transaction.getOrderGrantedValue();
        createdOn = transaction.getCreatedOn();
        createdBy = transaction.getCreatedBy();
    }

    public static class Builder {
        private ContributionTransactionDTO dto;
        
        public Builder() {
            dto = new ContributionTransactionDTO();
        }
        
        public Builder setId(Long id) {
            dto.setId(id);
            return this;
        }
        
        public Builder setRegisterId(Long registerId) {
            dto.setRegisterId(registerId);
            return this;
        }
        
        public Builder setOrderGrantedValue(BigDecimal orderGrantedValue) {
            dto.setOrderGrantedValue(orderGrantedValue);
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
        
        public ContributionTransactionDTO build() {
            return dto;
        }
    }

    public BigDecimal getOrderGrantedValue() {
        return orderGrantedValue;
    }

    public void setOrderGrantedValue(BigDecimal orderGrantedValue) {
        this.orderGrantedValue = orderGrantedValue;
    }
}
