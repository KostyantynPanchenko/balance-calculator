package balance.calculator.service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import balance.calculator.repository.BalanceDAO;
import balance.calculator.repository.ConsumptionTransactionDAO;
import balance.calculator.repository.ContributionTransactionDAO;
import balance.calculator.repository.RegisterDAO;
import balance.calculator.repository.StoreDAO;
import balance.calculator.repository.TransactionAllocationDAO;
import balance.calculator.repository.configuration.RepoConfig;
import balance.calculator.service.BalanceService;
import balance.calculator.service.ConsumptionTransactionService;
import balance.calculator.service.ContributionTransactionService;
import balance.calculator.service.RegisterService;
import balance.calculator.service.StoreService;
import balance.calculator.service.TransactionAllocationService;
import balance.calculator.service.impl.BalanceServiceImpl;
import balance.calculator.service.impl.ConsumptionTransactionServiceImpl;
import balance.calculator.service.impl.ContributionTransactionServiceImpl;
import balance.calculator.service.impl.RegisterServiceImpl;
import balance.calculator.service.impl.StoreServiceImpl;
import balance.calculator.service.impl.TransactionAllocationServiceImpl;

@Configuration
@Import(RepoConfig.class)
public class ServiceConfig {

    @Bean
    public StoreService getStoreService(StoreDAO storeDao) {
        return new StoreServiceImpl(storeDao);
    }
    
    @Bean
    public RegisterService getRegisterService(RegisterDAO registerDao) {
        return new RegisterServiceImpl(registerDao);
    }
    
    @Bean
    public ContributionTransactionService geContributionTransactionService(ContributionTransactionDAO contributionDao) {
        return new ContributionTransactionServiceImpl(contributionDao);
    }
    
    @Bean
    public ConsumptionTransactionService geConsumptionTransactionService(ConsumptionTransactionDAO consumptionDao) {
        return new ConsumptionTransactionServiceImpl(consumptionDao);
    }
    
    @Bean
    public BalanceService getBalanceService(BalanceDAO balanceDao, RegisterDAO registerDao) {
        return new BalanceServiceImpl(balanceDao, registerDao);
    }
    
    @Bean
    public TransactionAllocationService getTransactionAllocationService(TransactionAllocationDAO transactionAllocationDao) {
        return new TransactionAllocationServiceImpl(transactionAllocationDao);
    }
}
