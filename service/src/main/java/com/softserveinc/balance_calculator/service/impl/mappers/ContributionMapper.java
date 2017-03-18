package com.softserveinc.balance_calculator.service.impl.mappers;

import com.softserveinc.balance_calculator.domain.ContributionTransaction;
import com.softserveinc.balance_calculator.dto.ContributionTransactionDTO;

public class ContributionMapper {

    public static ContributionTransaction toDomainObject(ContributionTransactionDTO contribution) {
        return new ContributionTransaction.Builder()
                .setRegisterId(contribution.getRegisterId())
                .setOrderGrantedValue(contribution.getOrderGrantedValue())
                .setCreatedBy(contribution.getCreatedBy())
                .build();
    }
}
