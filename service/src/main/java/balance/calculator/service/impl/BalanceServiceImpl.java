package balance.calculator.service.impl;

import java.time.LocalDate;
import java.time.ZoneOffset;

import balance.calculator.domain.Balance;
import balance.calculator.domain.Register;
import balance.calculator.dto.BalanceDTO;
import balance.calculator.repository.BalanceDAO;
import balance.calculator.repository.RegisterDAO;
import balance.calculator.service.BalanceService;
import balance.calculator.service.impl.mappers.BalanceMapper;

public class BalanceServiceImpl implements BalanceService {

    private BalanceDAO balanceDao;
    private RegisterDAO registerDao;
    
    public BalanceServiceImpl(BalanceDAO balanceDao, RegisterDAO registerDao) {
        this.balanceDao = balanceDao;
        this.registerDao = registerDao;
    }
    
    @Override
    public BalanceDTO getCurrentBalance(Long registerId) {
        return getBalance(registerDao.getRegisterById(registerId), balanceDao.getCurrentBalance(registerId));
    }

    @Override
    public BalanceDTO getBalanceForDate(Long registerId, LocalDate date) {
        return getBalance(registerDao.getRegisterById(registerId), balanceDao.getBalanceForDate(registerId, date));
    }

    private BalanceDTO getBalance(Register register, Balance balance) {
        if (register == null || balance == null) {
            return null;
        }
        transformTimezone(register, balance);
        return BalanceMapper.toBalanceDTO(balance);
    }

    private void transformTimezone(Register register, Balance balance) {
        ZoneOffset offset = ZoneOffset.of(register.getTimezone());
        balance.setCreatedOn(balance.getCreatedOn().withOffsetSameInstant(offset));
        balance.setCreatedBy(register.getName());
    }

}
