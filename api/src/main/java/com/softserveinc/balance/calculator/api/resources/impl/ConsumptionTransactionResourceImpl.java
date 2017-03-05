package com.softserveinc.balance.calculator.api.resources.impl;

import java.util.List;

import javax.ws.rs.core.Response;

import com.softserveinc.balance.calculator.api.resources.ConsumptionTransactionResource;
import com.softserveinc.balance.calculator.dto.ConsumptionTransactionDTO;
import com.softserveinc.balance.calculator.service.ConsumptionTransactionService;

public class ConsumptionTransactionResourceImpl implements ConsumptionTransactionResource {

    private ConsumptionTransactionService consumptionService;
    
    public ConsumptionTransactionResourceImpl(ConsumptionTransactionService consumptionService) {
        this.consumptionService = consumptionService;
    }
    
    @Override
    public Response saveConsumptions(List<ConsumptionTransactionDTO> consumptions) {
        consumptionService.saveAll(consumptions);

        return Response.created(null).entity(consumptions).build();
    }

}
