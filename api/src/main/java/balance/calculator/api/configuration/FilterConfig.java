package balance.calculator.api.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.codahale.metrics.health.HealthCheck;

import balance.calculator.api.filters.CrossOriginFilter;
import balance.calculator.api.filters.PreMatchingFilter;
import balance.calculator.api.filters.RegisterFilter;
import balance.calculator.api.filters.StoreFilter;
import balance.calculator.api.health.BalanceCalculatorDatabaseHealthCheck;
import balance.calculator.service.RegisterService;
import balance.calculator.service.StoreService;
import balance.calculator.service.configuration.ServiceConfig;

@Configuration
@Import(ServiceConfig.class)
public class FilterConfig {

    @Bean
    public PreMatchingFilter getPrematchingFilter(StoreService storeService, RegisterService registerService) {
        return new PreMatchingFilter(storeService, registerService);
    }
    
    @Bean
    public StoreFilter getStoreFilter(StoreService storeService, RegisterService registerService) {
        return new StoreFilter(storeService, registerService);
    }
    
    @Bean
    public RegisterFilter getRegisterFilter(StoreService storeService, RegisterService registerService) {
        return new RegisterFilter(storeService, registerService);
    }
    
    @Bean
    public CrossOriginFilter getCrossOriginFilter() {
        return new CrossOriginFilter();
    }
    
    @Bean
    public HealthCheck getHealthCheck(DataSource dataSource) {
        return new BalanceCalculatorDatabaseHealthCheck(dataSource);
    }
}
