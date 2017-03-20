package com.softserveinc.balance.calculator.service.impl.mappers;

import com.softserveinc.balance.calculator.domain.ConsumptionTransaction;
import com.softserveinc.balance.calculator.dto.ConsumptionTransactionDTO;

public class ConsumptionMapper {

    public static ConsumptionTransaction toDomainObject(ConsumptionTransactionDTO consumption) {
        return new ConsumptionTransaction.Builder()
                .setRegisterId(consumption.getRegisterId())
                .setConsumedValue(consumption.getConsumedValue())
                .setCreatedBy(consumption.getCreatedBy())
                .build();
    }
}
