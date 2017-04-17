package balance.calculator.service.impl.mappers;

import balance.calculator.domain.ContributionTransaction;
import balance.calculator.dto.ContributionTransactionDTO;

public class ContributionMapper {

    public static ContributionTransaction toDomainObject(ContributionTransactionDTO contribution) {
        return new ContributionTransaction.Builder()
                .setRegisterId(contribution.getRegisterId())
                .setOrderGrantedValue(contribution.getOrderGrantedValue())
                .setCreatedBy(contribution.getCreatedBy())
                .build();
    }
}
