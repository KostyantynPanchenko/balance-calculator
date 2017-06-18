package balance.calculator.api.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import balance.calculator.service.configuration.ServiceConfig;

@Configuration
@ComponentScan("balance.calculator.api")
@Import(ServiceConfig.class)
public class AppConfig {

}
