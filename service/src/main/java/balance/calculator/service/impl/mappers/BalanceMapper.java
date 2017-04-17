package balance.calculator.service.impl.mappers;

import balance.calculator.domain.Balance;
import balance.calculator.dto.BalanceDTO;

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
