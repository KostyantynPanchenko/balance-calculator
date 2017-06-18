package balance.calculator.service.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import balance.calculator.repository.configuration.RepoConfig;

@Configuration
@Import(RepoConfig.class)
@ComponentScan("balance.calculator.service.impl")
public class ServiceConfig {

}
