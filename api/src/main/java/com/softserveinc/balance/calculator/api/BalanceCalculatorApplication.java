package com.softserveinc.balance.calculator.api;

import javax.sql.DataSource;
import javax.ws.rs.container.ContainerRequestFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.softserveinc.balance.calculator.api.filters.AuthorizationFilter;
import com.softserveinc.balance.calculator.api.health.BalanceCalculatorDatabaseHealthCheck;
import com.softserveinc.balance.calculator.api.resources.BalanceResource;
import com.softserveinc.balance.calculator.api.resources.ConsumptionTransactionResource;
import com.softserveinc.balance.calculator.api.resources.ContributionTransactionResource;
import com.softserveinc.balance.calculator.api.resources.RegisterResource;
import com.softserveinc.balance.calculator.api.resources.StoreResource;
import com.softserveinc.balance.calculator.api.resources.impl.BalanceResourceImpl;
import com.softserveinc.balance.calculator.api.resources.impl.ConsumptionTransactionResourceImpl;
import com.softserveinc.balance.calculator.api.resources.impl.ContributionTransactionResourceImpl;
import com.softserveinc.balance.calculator.api.resources.impl.RegisterResourceImpl;
import com.softserveinc.balance.calculator.api.resources.impl.StoreResourceImpl;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * The main class for BalanceCalculator applications.
 *
 * @param <BalanceCalculatorConfig> the type of configuration class for this application
 */
public class BalanceCalculatorApplication extends Application<BalanceCalculatorConfig> {
    
    public final static Logger LOGGER = LoggerFactory.getLogger(BalanceCalculatorApplication.class);
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
        ApplicationContext context = new ClassPathXmlApplicationContext(XML_CONFIG_LOCATION);
        
        final StoreResource storeResource = context.getBean(StoreResourceImpl.class);
        final RegisterResource registerResource = context.getBean(RegisterResourceImpl.class);
        final ConsumptionTransactionResource consumptionResource = context.getBean(ConsumptionTransactionResourceImpl.class);
        final ContributionTransactionResource contributionResource = context.getBean(ContributionTransactionResourceImpl.class);
        final DataSource dataSource = (DataSource) context.getBean(DriverManagerDataSource.class);
        final BalanceResource balanceResource = context.getBean(BalanceResourceImpl.class);
        final ContainerRequestFilter filter = context.getBean(AuthorizationFilter.class);
        
        environment.jersey().register(storeResource);
        environment.jersey().register(registerResource);
        environment.jersey().register(consumptionResource);
        environment.jersey().register(contributionResource);
        environment.jersey().register(balanceResource);
        environment.jersey().register(filter);
        
        final BalanceCalculatorDatabaseHealthCheck healthCheck = new BalanceCalculatorDatabaseHealthCheck(dataSource);
        environment.healthChecks().register(HEALTH_CHECK_NAME, healthCheck);
    }

    @Override
    public void initialize(Bootstrap<BalanceCalculatorConfig> bootstrap) {
        super.initialize(bootstrap);
    }

}
