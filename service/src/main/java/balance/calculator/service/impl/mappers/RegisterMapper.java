package balance.calculator.service.impl.mappers;

import balance.calculator.domain.Register;
import balance.calculator.dto.RegisterDTO;

public class RegisterMapper {

    public static Register toDomainObject(RegisterDTO dto) {
        return new Register.Builder()
                .setId(dto.getId())
                .setStoreId(dto.getStoreId())
                .setName(dto.getName())
                .setTimezone(dto.getTimezone())
                .build();
    }
    
    public static RegisterDTO toDTO(Register register) {
        return new RegisterDTO.Builder()
                .setId(register.getId())
                .setStoreId(register.getStoreId())
                .setName(register.getName())
                .setTimezone(register.getTimezone())
                .build();
    }
}
