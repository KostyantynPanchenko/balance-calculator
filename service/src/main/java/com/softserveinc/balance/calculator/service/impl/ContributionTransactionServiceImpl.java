package com.softserveinc.balance.calculator.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.softserveinc.balance.calculator.domain.ContributionTransaction;
import com.softserveinc.balance.calculator.dto.ContributionTransactionDTO;
import com.softserveinc.balance.calculator.repository.ContributionTransactionDAO;
import com.softserveinc.balance.calculator.service.ContributionTransactionService;
import com.softserveinc.balance.calculator.service.impl.mappers.ContributionMapper;

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
    public int[] saveAll(List<ContributionTransactionDTO> contributions) {
        List<ContributionTransaction> transactions = new ArrayList<>(contributions.size());
        contributions.forEach(contribution -> transactions.add(ContributionMapper.toDomainObject(contribution)));
        return contributionDao.saveAll(transactions);
    }
    
}
