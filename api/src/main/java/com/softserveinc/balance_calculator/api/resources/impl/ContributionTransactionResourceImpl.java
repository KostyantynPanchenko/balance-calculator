package com.softserveinc.balance_calculator.api.resources.impl;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserveinc.balance_calculator.api.resources.ContributionTransactionResource;
import com.softserveinc.balance_calculator.dto.ContributionTransactionDTO;
import com.softserveinc.balance_calculator.service.ContributionTransactionService;
import com.softserveinc.balance_calculator.service.TransactionAllocationService;
import com.softserveinc.balance_calculator.service.exception.ServiceException;

import io.dropwizard.jersey.errors.ErrorMessage;

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
        try {
            contributions.forEach(contribution -> contribution.setRegisterId(registerId));
            contributionService.saveAll(contributions);
            transactionAllocationService.registerAllocation(registerId);
            LOGGER.info("Successfully processed contribution transactions batch request for register {}.", registerId);
            return Response.ok().build();
        } catch (ServiceException e) {
            LOGGER.error("Could not execute batch processing.", e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ErrorMessage(500, e.getMessage())).build();
        }
    }

}
