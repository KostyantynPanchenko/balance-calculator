package com.softserveinc.balance_calculator.service.impl;

import java.time.LocalDate;

import com.softserveinc.balance_calculator.domain.Balance;
import com.softserveinc.balance_calculator.dto.BalanceDTO;
import com.softserveinc.balance_calculator.repository.BalanceDAO;
import com.softserveinc.balance_calculator.repository.exception.DomainEntityNotFoundException;
import com.softserveinc.balance_calculator.repository.exception.RepositoryException;
import com.softserveinc.balance_calculator.service.BalanceService;
import com.softserveinc.balance_calculator.service.exception.EntityNotFoundServiceException;
import com.softserveinc.balance_calculator.service.exception.ServiceException;

public class BalanceServiceImpl implements BalanceService {

    private BalanceDAO balanceDao;
    
    public BalanceServiceImpl(BalanceDAO balanceDao) {
        this.balanceDao = balanceDao;
    }
    
    @Override
    public BalanceDTO getCurrentBalance(Long registerId) throws EntityNotFoundServiceException, ServiceException {
        try {
            return toBalanceDTO(balanceDao.getCurrentBalance(registerId));
        } catch (DomainEntityNotFoundException e) {
            throw new EntityNotFoundServiceException(e);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public BalanceDTO getBalanceForDate(Long registerId, LocalDate date) throws EntityNotFoundServiceException, ServiceException {
        try {
            return toBalanceDTO(balanceDao.getBalanceForDate(registerId, date));
        } catch (DomainEntityNotFoundException e) {
            throw new EntityNotFoundServiceException(e);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    private BalanceDTO toBalanceDTO(Balance balance) {
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
