package com.softserveinc.balance.calculator.service.impl.mappers;

import com.softserveinc.balance.calculator.domain.Balance;
import com.softserveinc.balance.calculator.dto.BalanceDTO;

public class BalanceMapper {


    public static BalanceDTO toBalanceDTO(Balance balance) {
        return new BalanceDTO.Builder()
                .setId(balance.getId())
                .setRegisterId(balance.getRegisterId())
                .setCreatedOn(balance.getCreatedOn())
                .setCreatedBy(balance.getCreatedBy())
                .setTotalAllocatedConsumptionAmount(balance.getTotalAllocatedConsumptionAmount())
                .setTotalAllocatedContributionAmount(balance.getTotalAllocatedContributionAmount())
                .setTotalUnallocatedConsumptionAmount(balance.getTotalUnallocatedConsumptionAmount())
                .setTotalUnallocatedContributionAmount(balance.getTotalUnallocatedContributionAmount())
                .build();
    }
}
