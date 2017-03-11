package com.softserveinc.balance.calculator.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Balance {

    private Long id;
    private Long registerId;    
    private OffsetDateTime createdOn;
    private String createdBy;
    private BigDecimal totalAllocatedContributionAmount;
    private BigDecimal totalAllocatedConsumptionAmount;
    private BigDecimal totalUnallocatedContributionAmount;
    private BigDecimal totalUnallocatedConsumptionAmount;
    
    public Balance() {}
    
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

    public OffsetDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(OffsetDateTime createdOn) {
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
        Balance other = (Balance) obj;
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
        return "Balance [id=" + id + ", registerId=" + registerId + ", createdOn=" + createdOn + ", createdBy="
                + createdBy + ", totalAllocatedContributionAmount=" + totalAllocatedContributionAmount
                + ", totalAllocatedConsumptionAmount=" + totalAllocatedConsumptionAmount
                + ", totalUnallocatedContributionAmount=" + totalUnallocatedContributionAmount
                + ", totalUnallocatedConsumptionAmount=" + totalUnallocatedConsumptionAmount + "]";
    }
    
    
}
