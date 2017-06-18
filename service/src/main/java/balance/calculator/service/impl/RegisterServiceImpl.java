package balance.calculator.service.impl;

import org.springframework.stereotype.Component;

import balance.calculator.domain.Register;
import balance.calculator.dto.RegisterDTO;
import balance.calculator.repository.RegisterDAO;
import balance.calculator.service.RegisterService;
import balance.calculator.service.impl.mappers.RegisterMapper;

@Component
public class RegisterServiceImpl implements RegisterService {

    private RegisterDAO registerDao;
    
    public RegisterServiceImpl(RegisterDAO registerDao) {
        this.registerDao = registerDao;
    }
    
    public RegisterDTO getRegisterById(Long registerId) {
            Register register = registerDao.getRegisterById(registerId);
            return (register == null) ? null : RegisterMapper.toDTO(register);
    }

    public Long save(RegisterDTO registerDto) {
        return registerDao.insert(RegisterMapper.toDomainObject(registerDto));
    }

    public int update(RegisterDTO registerDto) {
        return registerDao.update(RegisterMapper.toDomainObject(registerDto));
    }

    public int delete(Long registerId) {
        return registerDao.delete(registerId);
    }

}
