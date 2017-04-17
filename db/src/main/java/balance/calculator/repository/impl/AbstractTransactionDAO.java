package balance.calculator.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import balance.calculator.domain.Transaction;
import balance.calculator.repository.TransactionDAO;

/**
 * Default implementation for <code>TransactionDAO</code>.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 10/04/2017
 *
 * @param <T> generic type of <code>Transaction</code>
 */
public abstract class AbstractTransactionDAO<T extends Transaction> implements TransactionDAO<T> {

    protected JdbcTemplate jdbcTemplate;
    protected String batchSQL;
    
    protected AbstractTransactionDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int[] saveAll(List<T> input) {
        List<Object[]> batch = new ArrayList<>(input.size());
        input.forEach(transaction -> batch.add(getBatchValues(transaction)));
        return jdbcTemplate.batchUpdate(batchSQL, batch);
    }
   
    /**
     * Prepares arguments for batch processing.
     * 
     * @param transaction <code>Transaction</code> object
     * @return an object holding values for batch processing
     */
    abstract Object[] getBatchValues(Transaction transaction);
}
