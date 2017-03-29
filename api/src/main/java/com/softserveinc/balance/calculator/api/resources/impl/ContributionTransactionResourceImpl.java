package com.softserveinc.balance.calculator.api.resources.impl;

import java.util.List;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserveinc.balance.calculator.api.resources.ContributionTransactionResource;
import com.softserveinc.balance.calculator.dto.ContributionTransactionDTO;
import com.softserveinc.balance.calculator.service.ContributionTransactionService;
import com.softserveinc.balance.calculator.service.TransactionAllocationService;

/**
 * Implementation of <code>ContributionTransactionResource</code> interface.
 *  
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @see ContributionTransactionResource
 * @since 06/03/2017
 */
public class ContributionTransactionResourceImpl implements ContributionTransactionResource {

    private final static Logger LOGGER = LoggerFactory.getLogger(ContributionTransactionResourceImpl.class);
    private ContributionTransactionService contributionService;
    private TransactionAllocationService transactionAllocationService;
    
    public ContributionTransactionResourceImpl(ContributionTransactionService contributionService, TransactionAllocationService transactionAllocationService) {
        this.contributionService = contributionService;
        this.transactionAllocationService = transactionAllocationService;
    }
    
    @Override
    public Response saveContributions(List<ContributionTransactionDTO> contributions, Long registerId) {
        LOGGER.info("Started processing contribution transactions batch request for register {}.", registerId);
        contributions.forEach(contribution -> contribution.setRegisterId(registerId));
        contributionService.saveAll(contributions);
        transactionAllocationService.registerAllocation(registerId);
        LOGGER.info("Successfully processed contribution transactions batch request for register {}.", registerId);
        return Response.ok().build();
    }

}
