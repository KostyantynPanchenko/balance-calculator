package com.softserveinc.balance_calculator.service.impl.mappers;

import com.softserveinc.balance_calculator.domain.Store;
import com.softserveinc.balance_calculator.dto.StoreDTO;

public class StoreMapper {

    public static Store toDomainObject(StoreDTO dto) {
        return new Store.Builder()
                .setId(dto.getId())
                .setTenantId(dto.getTenantId())
                .setName(dto.getName())
                .setDescription(dto.getDescription())
                .build();
    }
    
    public static StoreDTO toDTO(Store store) {
        return new StoreDTO.Builder()
                .setId(store.getId())
                .setTenantId(store.getTenantId())
                .setName(store.getName())
                .setDescription(store.getDescription())
                .build();
    }
}
