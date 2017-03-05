package com.softserveinc.balance.calculator.api;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.softserveinc.balance.calculator.api.resources.RegisterResource;
import com.softserveinc.balance.calculator.api.resources.StoreResource;
import com.softserveinc.balance.calculator.api.resources.TransactionResource;
import com.softserveinc.balance.calculator.api.resources.impl.RegisterResourceImpl;
import com.softserveinc.balance.calculator.api.resources.impl.StoreResourceImpl;
import com.softserveinc.balance.calculator.api.resources.impl.TransactionResourceImpl;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class BalanceCalculatorApplication extends Application<BalanceCalculatorConfig> {
    
    public static void main(String[] args) throws Exception {
        new BalanceCalculatorApplication().run(args);
    }
    
    @Override
    public void run(BalanceCalculatorConfig config, Environment environment) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("BalanceCalculator-context.xml");
        StoreResource storeResource = context.getBean(StoreResourceImpl.class);
        RegisterResource registerResource = context.getBean(RegisterResourceImpl.class);
        TransactionResource transactionResourrce = context.getBean(TransactionResourceImpl.class);
        
        environment.jersey().register(storeResource);
        environment.jersey().register(registerResource);
        environment.jersey().register(transactionResourrce);
        context.close();
    }

    @Override
    public void initialize(Bootstrap<BalanceCalculatorConfig> bootstrap) {
        super.initialize(bootstrap);
    }

}
