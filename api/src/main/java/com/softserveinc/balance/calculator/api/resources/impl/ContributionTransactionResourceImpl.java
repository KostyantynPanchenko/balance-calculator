package com.softserveinc.balance.calculator.api.resources.impl;

import java.util.List;

import javax.ws.rs.core.Response;

import com.softserveinc.balance.calculator.api.resources.ContributionTransactionResource;
import com.softserveinc.balance.calculator.domain.ContributionTransaction;
import com.softserveinc.balance.calculator.dto.StoreDTO;

public class ContributionTransactionResourceImpl implements ContributionTransactionResource {

    @Override
    public Response saveContributions(List<ContributionTransaction> contributions) {
        System.out.println(contributions);
        StoreDTO dto = new StoreDTO();
        dto.setDescription("SUCCESS");
        return Response.ok(dto).build();
    }

}
