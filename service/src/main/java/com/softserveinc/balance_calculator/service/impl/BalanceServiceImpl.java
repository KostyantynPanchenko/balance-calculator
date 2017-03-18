package com.softserveinc.balance_calculator.service.impl;

import java.time.LocalDate;
import java.time.ZoneOffset;

import com.softserveinc.balance_calculator.domain.Balance;
import com.softserveinc.balance_calculator.domain.Register;
import com.softserveinc.balance_calculator.dto.BalanceDTO;
import com.softserveinc.balance_calculator.repository.BalanceDAO;
import com.softserveinc.balance_calculator.repository.RegisterDAO;
import com.softserveinc.balance_calculator.repository.exception.RepositoryException;
import com.softserveinc.balance_calculator.service.BalanceService;
import com.softserveinc.balance_calculator.service.exceptions.ServiceException;
import com.softserveinc.balance_calculator.service.impl.mappers.BalanceMapper;

public class BalanceServiceImpl implements BalanceService {

    private BalanceDAO balanceDao;
    private RegisterDAO registerDao;
    
    public BalanceServiceImpl(BalanceDAO balanceDao, RegisterDAO registerDao) {
        this.balanceDao = balanceDao;
        this.registerDao = registerDao;
    }
    
    @Override
    public BalanceDTO getCurrentBalance(Long registerId) throws ServiceException {
        try {
            Register register = registerDao.getRegisterById(registerId);
            Balance balance = balanceDao.getCurrentBalance(registerId);
            if (register == null || balance == null) {
                return null;
            }
            transformTimezone(register, balance);
            return BalanceMapper.toBalanceDTO(balance);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    private void transformTimezone(Register register, Balance balance) {
        ZoneOffset offset = ZoneOffset.of(register.getTimezone());
        balance.setCreatedOn(balance.getCreatedOn().withOffsetSameInstant(offset));
        balance.setCreatedBy(register.getName());
    }

    @Override
    public BalanceDTO getBalanceForDate(Long registerId, LocalDate date) throws ServiceException {
        try {
            Register register = registerDao.getRegisterById(registerId);
            Balance balance = balanceDao.getBalanceForDate(registerId, date);
            if (register == null || balance == null) {
                return null;
            }
            transformTimezone(register, balance);
            return BalanceMapper.toBalanceDTO(balance);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

}
