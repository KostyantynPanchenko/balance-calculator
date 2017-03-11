package com.softserveinc.balance.calculator.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.softserveinc.balance.calculator.domain.ConsumptionTransaction;

public class ConsumptionTransactionDTO extends AbstractTransactionDTO {

    @NotNull
    @Min(0)
    private BigDecimal consumedValue;
    
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
        
        public Builder setCreatedOn(OffsetDateTime createdOn) {
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

    public BigDecimal getConsumedValue() {
        return consumedValue;
    }

    public void setConsumedValue(BigDecimal consumedValue) {
        this.consumedValue = consumedValue;
    }

    @Override
    public String toString() {
        return "ConsumptionTransactionDTO [id=" + id + ", registerId=" + registerId + "consumedValue="
                + consumedValue + ", createdOn=" + createdOn + ", createdBy=" + createdBy + "]";
    }
    
}
