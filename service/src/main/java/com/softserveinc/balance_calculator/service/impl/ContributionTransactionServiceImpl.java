package com.softserveinc.balance_calculator.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.softserveinc.balance_calculator.domain.ContributionTransaction;
import com.softserveinc.balance_calculator.dto.ContributionTransactionDTO;
import com.softserveinc.balance_calculator.repository.ContributionTransactionDAO;
import com.softserveinc.balance_calculator.repository.exception.RepositoryException;
import com.softserveinc.balance_calculator.service.ContributionTransactionService;
import com.softserveinc.balance_calculator.service.exception.ServiceException;

/**
 * Implementation of <code>ContributionTransactionService</code>.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @see ContributionTransactionService
 * @since 06/03/2017 * 
 */
public class ContributionTransactionServiceImpl implements ContributionTransactionService {

    private ContributionTransactionDAO contributionDao;
    
    public ContributionTransactionServiceImpl(ContributionTransactionDAO contributionDao) {
        this.contributionDao = contributionDao;
    }

    @Override
    public int[] saveAll(List<ContributionTransactionDTO> contributions) throws ServiceException {
        List<ContributionTransaction> input = new ArrayList<>(contributions.size());
        contributions.forEach(contribution -> input.add(newTransaction(contribution)));
        try {
            return contributionDao.saveAll(input);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    private ContributionTransaction newTransaction(ContributionTransactionDTO contribution) {
        return new ContributionTransaction.Builder()
                .setRegisterId(contribution.getRegisterId())
                .setOrderGrantedValue(contribution.getOrderGrantedValue())
                .setCreatedBy(contribution.getCreatedBy())
                .build();
    }
    
}
