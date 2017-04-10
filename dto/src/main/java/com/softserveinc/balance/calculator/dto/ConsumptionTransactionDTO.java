package com.softserveinc.balance.calculator.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ConsumptionTransactionDTO extends AbstractTransactionDTO {

    @NotNull
    @Min(0)
    private BigDecimal consumedValue;

    public static class Builder {
        private Long id;
        private Long registerId;
        private OffsetDateTime createdOn;
        private String createdBy;
        private BigDecimal consumedValue;
        
        public Builder setId(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder setRegisterId(Long registerId) {
            this.registerId = registerId;
            return this;
        }
        
        public Builder setConsumedValue(BigDecimal consumedValue) {
            this.consumedValue = consumedValue;
            return this;
        }
        
        public Builder setCreatedOn(OffsetDateTime createdOn) {
            this.createdOn = createdOn;
            return this;
        }
        
        public Builder setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
        }
        
        public ConsumptionTransactionDTO build() {
            ConsumptionTransactionDTO dto = new ConsumptionTransactionDTO();
            dto.setId(id);
            dto.setRegisterId(registerId);
            dto.setCreatedBy(createdBy);
            dto.setCreatedOn(createdOn);
            dto.setConsumedValue(consumedValue);
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
