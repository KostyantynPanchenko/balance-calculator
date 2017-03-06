package com.softserveinc.balance.calculator.service;

import java.util.List;

import com.softserveinc.balance.calculator.dto.ContributionTransactionDTO;
import com.softserveinc.balance.calculator.service.exception.ServiceException;

public interface ContributionTransactionService {

    int[] saveAll(List<ContributionTransactionDTO> contributions) throws ServiceException;

}
