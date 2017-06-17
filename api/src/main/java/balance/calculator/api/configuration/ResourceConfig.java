package balance.calculator.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import balance.calculator.api.resources.BalanceResource;
import balance.calculator.api.resources.ConsumptionTransactionResource;
import balance.calculator.api.resources.ContributionTransactionResource;
import balance.calculator.api.resources.RegisterResource;
import balance.calculator.api.resources.StoreResource;
import balance.calculator.api.resources.impl.BalanceResourceImpl;
import balance.calculator.api.resources.impl.ConsumptionTransactionResourceImpl;
import balance.calculator.api.resources.impl.ContributionTransactionResourceImpl;
import balance.calculator.api.resources.impl.RegisterResourceImpl;
import balance.calculator.api.resources.impl.StoreResourceImpl;
import balance.calculator.service.BalanceService;
import balance.calculator.service.ConsumptionTransactionService;
import balance.calculator.service.ContributionTransactionService;
import balance.calculator.service.RegisterService;
import balance.calculator.service.StoreService;
import balance.calculator.service.TransactionAllocationService;
import balance.calculator.service.configuration.ServiceConfig;

@Configuration
@Import(ServiceConfig.class)
public class ResourceConfig {

    @Bean
    public StoreResource getStoreResource(StoreService storeService) {
        return new StoreResourceImpl(storeService);
    }
    
    @Bean
    public RegisterResource getRegisterResource(RegisterService registerService) {
        return new RegisterResourceImpl(registerService);
    }
    
    @Bean
    public ContributionTransactionResource getContributionTransactionResource(ContributionTransactionService contributionService, TransactionAllocationService transactionAllocationService) {
        return new ContributionTransactionResourceImpl(contributionService, transactionAllocationService);
    }
    
    @Bean
    public ConsumptionTransactionResource getConsumptionTransactionResource(ConsumptionTransactionService consumptionService, TransactionAllocationService transactionAllocationService) {
        return new ConsumptionTransactionResourceImpl(consumptionService, transactionAllocationService);
    }
    
    @Bean
    public BalanceResource getBalanceResource(BalanceService balanceService) {
        return new BalanceResourceImpl(balanceService);
    }
    
}
