package balance.calculator.service.impl.mappers;

import balance.calculator.domain.ConsumptionTransaction;
import balance.calculator.dto.ConsumptionTransactionDTO;

public class ConsumptionMapper {

    public static ConsumptionTransaction toDomainObject(ConsumptionTransactionDTO consumption) {
        return new ConsumptionTransaction.Builder()
                .setRegisterId(consumption.getRegisterId())
                .setConsumedValue(consumption.getConsumedValue())
                .setCreatedBy(consumption.getCreatedBy())
                .build();
    }
}
