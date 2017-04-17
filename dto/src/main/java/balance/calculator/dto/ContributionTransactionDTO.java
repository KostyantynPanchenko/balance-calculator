package balance.calculator.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ContributionTransactionDTO extends AbstractTransactionDTO {

    @NotNull
    @Min(0)
    private BigDecimal orderGrantedValue;

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
        
        public ContributionTransactionDTO build() {
            ContributionTransactionDTO dto = new ContributionTransactionDTO();
            dto.setId(id);
            dto.setRegisterId(registerId);
            dto.setCreatedBy(createdBy);
            dto.setCreatedOn(createdOn);
            dto.setOrderGrantedValue(orderGrantedValue);
            return dto;
        }
    }

    public BigDecimal getOrderGrantedValue() {
        return orderGrantedValue;
    }

    public void setOrderGrantedValue(BigDecimal orderGrantedValue) {
        this.orderGrantedValue = orderGrantedValue;
    }

    @Override
    public String toString() {
        return "ContributionTransactionDTO [id=" + id + ", registerId=" + registerId + "orderGrantedValue="
                + orderGrantedValue + ", createdOn=" + createdOn + ", createdBy=" + createdBy + "]";
    }
    
    
}
