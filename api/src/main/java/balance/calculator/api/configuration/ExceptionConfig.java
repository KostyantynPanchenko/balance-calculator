package balance.calculator.api.configuration;

import javax.ws.rs.ext.ExceptionMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import balance.calculator.api.exception.mappers.DataAccessExceptionMapper;
import balance.calculator.api.exception.mappers.DataIntegrityExceptionMapper;

@Configuration
public class ExceptionConfig {

    @Bean
    public ExceptionMapper<DataAccessException> getDataAccessExceptionMapper() {
        return new DataAccessExceptionMapper();
    }
    
    @Bean
    public ExceptionMapper<DataIntegrityViolationException> getDataIntegrityExceptionMapper() {
        return new DataIntegrityExceptionMapper();
    }
}
