package com.softserveinc.balance.calculator.api.resources.impl;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserveinc.balance.calculator.api.resources.ConsumptionTransactionResource;
import com.softserveinc.balance.calculator.dto.ConsumptionTransactionDTO;
import com.softserveinc.balance.calculator.service.ConsumptionTransactionService;
import com.softserveinc.balance.calculator.service.exception.ServiceException;

import io.dropwizard.jersey.errors.ErrorMessage;

/**
 * Implementation of <code>ContributionTransactionResource</code> interface.
 *  
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @see ConsumptionTransactionResource
 * @since 06/03/2017
 */
public class ConsumptionTransactionResourceImpl implements ConsumptionTransactionResource {

    private final static Logger LOGGER = LoggerFactory.getLogger(ConsumptionTransactionResourceImpl.class);
    private ConsumptionTransactionService consumptionService;
    
    public ConsumptionTransactionResourceImpl(ConsumptionTransactionService consumptionService) {
        this.consumptionService = consumptionService;
    }
    
    @Override
    public Response saveConsumptions(List<ConsumptionTransactionDTO> consumptions) {
        LOGGER.info("Started processing consumption transactions batch request.");
        try {
            consumptionService.saveAll(consumptions);
            LOGGER.info("Successfully processed consumption transactions batch request.");
            return Response.ok().build();
        } catch (ServiceException e) {
            LOGGER.error("Could not execute batch processing.", e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ErrorMessage(500, e.getMessage())).build();
        }
    }

}
