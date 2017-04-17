package balance.calculator.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class ContributionTransaction extends Transaction {

    private BigDecimal orderGrantedValue;
    
    public ContributionTransaction() { }
    
    public static class Builder {
        private Long id;
        private Long registerId;
        private OffsetDateTime createdOn;
        private String createdBy;
        private BigDecimal orderGrantedValue;
        
        public Builder setId(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder setRegisterId(Long registerId) {
            this.registerId = registerId;
            return this;
        }
        
        public Builder setOrderGrantedValue(BigDecimal orderGrantedValue) {
            this.orderGrantedValue = orderGrantedValue;
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
        
        public ContributionTransaction build() {
            ContributionTransaction dto = new ContributionTransaction();
            dto.setId(id);
            dto.setRegisterId(registerId);
            dto.setCreatedBy(createdBy);
            dto.setCreatedOn(createdOn);
            dto.setOrderGrantedValue(orderGrantedValue);
            return dto;
        }
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

    public void setOrderGrantedValue(BigDecimal orderGrantedValue) {
        this.orderGrantedValue = orderGrantedValue;
    }

    @Override
    public String toString() {
        return "ContributionTransaction [id=" + id + ", registerId=" + registerId + "orderGrantedValue=" 
                + orderGrantedValue + ", createdOn=" + createdOn + ", createdBy=" + createdBy + "]";
    }

    public Object getOrderGrantedValue() {
        return this.orderGrantedValue;
    }
    
    
}
