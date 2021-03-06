package balance.calculator.api.health;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.codahale.metrics.health.HealthCheck;

/**
 * Health check for database connection.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 06/03/2017
 */
@Component
public class BalanceCalculatorDatabaseHealthCheck extends HealthCheck {

    private DataSource dataSource;
    
    public BalanceCalculatorDatabaseHealthCheck(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Override
    protected Result check() throws Exception {
        if (dataSource != null) {
            if (dataSource.getConnection() != null) {
                return Result.healthy("Database connection is alive.");
            }
        }
        return Result.unhealthy("No connection with database.");
    }

}
