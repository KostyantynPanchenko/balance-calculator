package balance.calculator.api;

import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import balance.calculator.api.configuration.AppConfig;
import balance.calculator.api.exception.mappers.DataAccessExceptionMapper;
import balance.calculator.api.exception.mappers.DataIntegrityExceptionMapper;
import balance.calculator.api.filters.CrossOriginFilter;
import balance.calculator.api.filters.PreMatchingFilter;
import balance.calculator.api.filters.RegisterFilter;
import balance.calculator.api.filters.StoreFilter;
import balance.calculator.api.health.BalanceCalculatorDatabaseHealthCheck;
import balance.calculator.api.resources.BalanceResource;
import balance.calculator.api.resources.ConsumptionTransactionResource;
import balance.calculator.api.resources.ContributionTransactionResource;
import balance.calculator.api.resources.RegisterResource;
import balance.calculator.api.resources.StoreResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * The main class for BalanceCalculator applications.
 *
 * @param BalanceCalculatorConfig the type of configuration class for this application
 */
public class BalanceCalculatorApplication extends Application<BalanceCalculatorConfig> {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(BalanceCalculatorApplication.class);
    private final static String STARTING = "Starting application...";
    private final String XML_CONFIG_LOCATION = "classpath:BalanceCalculator-context.xml";
    private final String HEALTH_CHECK_NAME = "BalanceCalculator App";
    
    public static void main(String[] args) throws Exception {
        LOGGER.info(STARTING);
        new BalanceCalculatorApplication().run(args);
    }
    
    @Override
    public void run(BalanceCalculatorConfig config, Environment environment) throws Exception {

        @SuppressWarnings("resource")
//        ApplicationContext context = new ClassPathXmlApplicationContext(XML_CONFIG_LOCATION);
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        StoreResource storeResource = context.getBean(StoreResource.class);
        RegisterResource registerResource = context.getBean(RegisterResource.class);
        ConsumptionTransactionResource consumptionResource = context.getBean(ConsumptionTransactionResource.class);
        ContributionTransactionResource contributionResource = context.getBean(ContributionTransactionResource.class);
        BalanceResource balanceResource = context.getBean(BalanceResource.class);
        BalanceCalculatorDatabaseHealthCheck healthCheck = context.getBean(BalanceCalculatorDatabaseHealthCheck.class);
        
        ContainerRequestFilter preMatchingFilter = context.getBean(PreMatchingFilter.class);
        ContainerRequestFilter storeFilter = context.getBean(StoreFilter.class);
        ContainerRequestFilter registerFilter = context.getBean(RegisterFilter.class);
        ContainerResponseFilter corsFilter = context.getBean(CrossOriginFilter.class);
        
        ExceptionMapper<DataAccessException> access = context.getBean(DataAccessExceptionMapper.class);
        ExceptionMapper<DataIntegrityViolationException> violation = context.getBean(DataIntegrityExceptionMapper.class);
        
        environment.jersey().register(storeResource);
        environment.jersey().register(registerResource);
        environment.jersey().register(consumptionResource);
        environment.jersey().register(contributionResource);
        environment.jersey().register(balanceResource);
        environment.jersey().register(preMatchingFilter);
        environment.jersey().register(storeFilter);
        environment.jersey().register(registerFilter);
        environment.jersey().register(corsFilter);
        environment.jersey().register(access);
        environment.jersey().register(violation);
        environment.healthChecks().register(HEALTH_CHECK_NAME, healthCheck);
    }

    @Override
    public void initialize(Bootstrap<BalanceCalculatorConfig> bootstrap) {
        super.initialize(bootstrap);
    }

}
