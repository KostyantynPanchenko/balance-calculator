package com.softserveinc.balance.calculator.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Balance {

    private long id;
    private long registerId;
    private Timestamp createdOn;
    private String createdBy;
    private BigDecimal totalAllocatedContributionAmount;
    private BigDecimal totalAllocatedConsumptionAmount;
    private BigDecimal totalUnallocatedContributionAmount;
    private BigDecimal totalUnallocatedConsumptionAmount;
    
    public Balance() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRegisterId() {
        return registerId;
    }

    public void setRegisterId(long registerId) {
        this.registerId = registerId;
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
}
