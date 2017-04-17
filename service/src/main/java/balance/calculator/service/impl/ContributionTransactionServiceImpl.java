package balance.calculator.service.impl;

import java.util.ArrayList;
import java.util.List;

import balance.calculator.domain.ContributionTransaction;
import balance.calculator.dto.ContributionTransactionDTO;
import balance.calculator.repository.ContributionTransactionDAO;
import balance.calculator.service.ContributionTransactionService;
import balance.calculator.service.impl.mappers.ContributionMapper;

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
