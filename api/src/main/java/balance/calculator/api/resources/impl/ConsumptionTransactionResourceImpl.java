package balance.calculator.api.resources.impl;

import java.util.List;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import balance.calculator.api.resources.ConsumptionTransactionResource;
import balance.calculator.dto.ConsumptionTransactionDTO;
import balance.calculator.service.ConsumptionTransactionService;
import balance.calculator.service.TransactionAllocationService;

/**
 * Implementation of <code>ConsumptionTransactionResource</code> interface.
 *  
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @see ConsumptionTransactionResource
 * @since 06/03/2017
 */
@Component
public class ConsumptionTransactionResourceImpl implements ConsumptionTransactionResource {

    private final static Logger LOGGER = LoggerFactory.getLogger(ConsumptionTransactionResourceImpl.class);
    private ConsumptionTransactionService consumptionService;
    private TransactionAllocationService transactionAllocationService;
    
    public ConsumptionTransactionResourceImpl(ConsumptionTransactionService consumptionService, TransactionAllocationService transactionAllocationService) {
        this.consumptionService = consumptionService;
        this.transactionAllocationService = transactionAllocationService;
    }
    
    @Override
    public Response saveConsumptions(List<ConsumptionTransactionDTO> consumptions, Long registerId) {
        LOGGER.info("Started processing consumption transactions batch request for register {}.", registerId);
        consumptions.forEach(consumption -> consumption.setRegisterId(registerId));
        consumptionService.saveAll(consumptions);
        transactionAllocationService.registerAllocation(registerId);
        LOGGER.info("Successfully processed consumption transactions batch request for register {}.", registerId);
        return Response.ok().build();
    }

}
