package balance.calculator.repository.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import balance.calculator.repository.BalanceDAO;
import balance.calculator.repository.ConsumptionTransactionDAO;
import balance.calculator.repository.ContributionTransactionDAO;
import balance.calculator.repository.RegisterDAO;
import balance.calculator.repository.StoreDAO;
import balance.calculator.repository.TransactionAllocationDAO;
import balance.calculator.repository.impl.BalanceDAOImpl;
import balance.calculator.repository.impl.ConsumptionTransactionDAOImpl;
import balance.calculator.repository.impl.ContributionTransactionDAOImpl;
import balance.calculator.repository.impl.RegisterDAOImpl;
import balance.calculator.repository.impl.StoreDAOImpl;
import balance.calculator.repository.impl.TransactionAllocationDAOImpl;
import liquibase.integration.spring.SpringLiquibase;

@Configuration
@PropertySource("classpath:jdbc.properties")
public class RepoConfig {

    private static final String CHANGELOG = "classpath:changelog-master.xml";
    private static final String CONTEXTS = "test, production";
        
    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.driverClassName}")
    private String driverClassName;
    
    @Value("${jdbc.url}")
    private String url;
    
    @Bean
    public StoreDAO getStoreDao(JdbcTemplate jdbcTemplate) {
        return new StoreDAOImpl(jdbcTemplate);
    }
    
    @Bean 
    public RegisterDAO getRegisterDao(JdbcTemplate jdbcTemplate) {
        return new RegisterDAOImpl(jdbcTemplate);
    }
    
    @Bean 
    public ConsumptionTransactionDAO getConsumptionTransactionDao(JdbcTemplate jdbcTemplate) {
        return new ConsumptionTransactionDAOImpl(jdbcTemplate);
    }
    
    @Bean 
    public ContributionTransactionDAO getContributionTransactionDao(JdbcTemplate jdbcTemplate) {
        return new ContributionTransactionDAOImpl(jdbcTemplate);
    }
    
    @Bean
    public TransactionAllocationDAO getTransactionAllocationDao(DataSource dataSource) {
        return new TransactionAllocationDAOImpl(dataSource);
    }
    
    @Bean
    public BalanceDAO getBalanceDao(JdbcTemplate jdbcTemplate) {
        return new BalanceDAOImpl(jdbcTemplate);
    }
    
    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }
    
    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    
    @Bean
    public SpringLiquibase getLiquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(CHANGELOG);
        liquibase.setContexts(CONTEXTS);
        return liquibase;
    }
}
