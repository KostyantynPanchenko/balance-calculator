package com.softserveinc.balance_calculator.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class BalanceDTO {

    private Long id;
    private Long registerId;
    private String createdOn;
    private String createdBy;
    private BigDecimal totalAllocatedContributionAmount;
    private BigDecimal totalAllocatedConsumptionAmount;
    private BigDecimal totalUnallocatedContributionAmount;
    private BigDecimal totalUnallocatedConsumptionAmount;
    
    public BalanceDTO() {}

    public static class Builder {
        private BalanceDTO balance;
        
        public Builder() {
            balance = new BalanceDTO();
        }
        
        public Builder setId(Long id) {
            balance.setId(id);
            return this;
        }
        
        public Builder setRegisterId(Long registerId) {
            balance.setRegisterId(registerId);
            return this;
        }
        
        public Builder setCreatedOn(OffsetDateTime createdOn) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
            balance.setCreatedOn(createdOn.format(formatter));
            return this;
        }
        
        public Builder setCreatedBy(String createdBy) {
            balance.setCreatedBy(createdBy);
            return this;
        }
        
        public Builder setTotalAllocatedContributionAmount(BigDecimal amount) {
            balance.totalAllocatedContributionAmount = amount;
            return this;
        }
        
        public Builder setTotalAllocatedConsumptionAmount(BigDecimal amount) {
            balance.totalAllocatedConsumptionAmount = amount;
            return this;
        }
        
        public Builder setTotalUnallocatedContributionAmount(BigDecimal amount) {
            balance.totalUnallocatedContributionAmount = amount;
            return this;
        }
        
        public Builder setTotalUnallocatedConsumptionAmount(BigDecimal amount) {
            balance.totalUnallocatedConsumptionAmount = amount;
            return this;
        }
        
        public BalanceDTO build() {
            return balance;
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

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public BigDecimal getTotalAllocatedContributionAmount() {
        return totalAllocatedContributionAmount;
    }

    public void setTotalAllocatedContributionAmount(BigDecimal totalAllocatedContributionAmount) {
        this.totalAllocatedContributionAmount = totalAllocatedContributionAmount;
    }

    public BigDecimal getTotalAllocatedConsumptionAmount() {
        return totalAllocatedConsumptionAmount;
    }

    public void setTotalAllocatedConsumptionAmount(BigDecimal totalAllocatedConsumptionAmount) {
        this.totalAllocatedConsumptionAmount = totalAllocatedConsumptionAmount;
    }

    public BigDecimal getTotalUnallocatedContributionAmount() {
        return totalUnallocatedContributionAmount;
    }

    public void setTotalUnallocatedContributionAmount(BigDecimal totalUnallocatedContributionAmount) {
        this.totalUnallocatedContributionAmount = totalUnallocatedContributionAmount;
    }

    public BigDecimal getTotalUnallocatedConsumptionAmount() {
        return totalUnallocatedConsumptionAmount;
    }

    public void setTotalUnallocatedConsumptionAmount(BigDecimal totalUnallocatedConsumptionAmount) {
        this.totalUnallocatedConsumptionAmount = totalUnallocatedConsumptionAmount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((createdBy == null) ? 0 : createdBy.hashCode());
        result = prime * result
                + ((createdOn == null) ? 0 : createdOn.hashCode());
        result = prime * result
                + ((registerId == null) ? 0 : registerId.hashCode());
        result = prime * result + ((totalAllocatedConsumptionAmount == null) ? 0
                : totalAllocatedConsumptionAmount.hashCode());
        result = prime * result + ((totalAllocatedContributionAmount == null)
                ? 0 : totalAllocatedContributionAmount.hashCode());
        result = prime * result + ((totalUnallocatedConsumptionAmount == null)
                ? 0 : totalUnallocatedConsumptionAmount.hashCode());
        result = prime * result + ((totalUnallocatedContributionAmount == null)
                ? 0 : totalUnallocatedContributionAmount.hashCode());
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
        BalanceDTO other = (BalanceDTO) obj;
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
        if (totalAllocatedConsumptionAmount == null) {
            if (other.totalAllocatedConsumptionAmount != null)
                return false;
        } else if (!totalAllocatedConsumptionAmount
                .equals(other.totalAllocatedConsumptionAmount))
            return false;
        if (totalAllocatedContributionAmount == null) {
            if (other.totalAllocatedContributionAmount != null)
                return false;
        } else if (!totalAllocatedContributionAmount
                .equals(other.totalAllocatedContributionAmount))
            return false;
        if (totalUnallocatedConsumptionAmount == null) {
            if (other.totalUnallocatedConsumptionAmount != null)
                return false;
        } else if (!totalUnallocatedConsumptionAmount
                .equals(other.totalUnallocatedConsumptionAmount))
            return false;
        if (totalUnallocatedContributionAmount == null) {
            if (other.totalUnallocatedContributionAmount != null)
                return false;
        } else if (!totalUnallocatedContributionAmount
                .equals(other.totalUnallocatedContributionAmount))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "BalanceDTO [id=" + id + ", registerId=" + registerId + ", createdOn=" + createdOn + ", createdBy="
                + createdBy + ", totalAllocatedContributionAmount=" + totalAllocatedContributionAmount
                + ", totalAllocatedConsumptionAmount=" + totalAllocatedConsumptionAmount
                + ", totalUnallocatedContributionAmount=" + totalUnallocatedContributionAmount
                + ", totalUnallocatedConsumptionAmount=" + totalUnallocatedConsumptionAmount + "]";
    }
    
    
}
