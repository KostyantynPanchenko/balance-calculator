package com.softserveinc.balance.calculator.api.resources.impl;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserveinc.balance.calculator.api.exception.ErrorMessage;
import com.softserveinc.balance.calculator.api.resources.ContributionTransactionResource;
import com.softserveinc.balance.calculator.dto.ContributionTransactionDTO;
import com.softserveinc.balance.calculator.service.ContributionTransactionService;
import com.softserveinc.balance.calculator.service.exception.ServiceException;

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
    
    public ContributionTransactionResourceImpl(ContributionTransactionService contributionService) {
        this.contributionService = contributionService;
    }
    
    @Override
    public Response saveContributions(List<ContributionTransactionDTO> contributions) {
        LOGGER.info("Started processing contribution transactions batch request.");
        try {
            contributionService.saveAll(contributions);
            LOGGER.info("Successfully processed contribution transactions batch request.");
            return Response.ok().build();
        } catch (ServiceException e) {
            LOGGER.error("Could not execute batch processing.", e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ErrorMessage(500, e.getMessage())).build();
        }
    }

}
