package balance.calculator.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class ConsumptionTransaction extends Transaction {

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
        
        public ConsumptionTransaction build() {
            ConsumptionTransaction dto = new ConsumptionTransaction();
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

    @Override
    public String toString() {
        return "ConsumptionTransaction [id=" + id + ", registerId=" + registerId + "consumedValue=" + consumedValue
                + ", createdOn=" + createdOn + ", createdBy=" + createdBy + "]";
    }
    
    
}
