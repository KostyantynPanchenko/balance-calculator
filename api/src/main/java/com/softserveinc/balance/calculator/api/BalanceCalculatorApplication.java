package com.softserveinc.balance.calculator.api;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.softserveinc.balance.calculator.api.resources.StoreResource;
import com.softserveinc.balance.calculator.api.resources.impl.StoreResourceImpl;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class BalanceCalculatorApplication extends Application<BalanceCalculatorConfig> {

    @Override
    public void run(BalanceCalculatorConfig config, Environment environment) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("BalanceCalculator-context.xml");
        StoreResource storeResource = context.getBean(StoreResourceImpl.class);
        
        environment.jersey().register(storeResource);
    }

    @Override
    public void initialize(Bootstrap<BalanceCalculatorConfig> bootstrap) {
        super.initialize(bootstrap);
    }
    
    public static void main(String[] args) throws Exception {
        new BalanceCalculatorApplication().run(args);
    }

}
