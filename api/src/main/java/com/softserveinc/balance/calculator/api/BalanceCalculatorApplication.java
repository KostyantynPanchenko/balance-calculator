package com.softserveinc.balance.calculator.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.softserveinc.balance.calculator.api.resources.RegisterResource;
import com.softserveinc.balance.calculator.api.resources.StoreResource;
import com.softserveinc.balance.calculator.api.resources.ConsumptionTransactionResource;
import com.softserveinc.balance.calculator.api.resources.impl.RegisterResourceImpl;
import com.softserveinc.balance.calculator.api.resources.impl.StoreResourceImpl;
import com.softserveinc.balance.calculator.api.resources.impl.ConsumptionTransactionResourceImpl;

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
    
    public static void main(String[] args) throws Exception {
        LOGGER.info("Starting application.");
        new BalanceCalculatorApplication().run(args);
    }
    
    @Override
    public void run(BalanceCalculatorConfig config, Environment environment) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("BalanceCalculator-context.xml");
        StoreResource storeResource = context.getBean(StoreResourceImpl.class);
        RegisterResource registerResource = context.getBean(RegisterResourceImpl.class);
        ConsumptionTransactionResource consumptionResource = context.getBean(ConsumptionTransactionResourceImpl.class);
        
        environment.jersey().register(storeResource);
        environment.jersey().register(registerResource);
        environment.jersey().register(consumptionResource);
        context.close();
    }

    @Override
    public void initialize(Bootstrap<BalanceCalculatorConfig> bootstrap) {
        super.initialize(bootstrap);
    }

}
