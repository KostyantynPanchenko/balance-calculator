package com.softserveinc.balance_calculator.repository.exception;

/**
 * Exception thrown when an attempt to insert or update data results in violation of an integrity constraint.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 06/03/2017
 */
public class DataIntegrityViolationRepositoryException extends RepositoryException {

    private static final long serialVersionUID = 1L;
    
    public DataIntegrityViolationRepositoryException() { }
    
    public DataIntegrityViolationRepositoryException(String message) {
        super(message);
    }

    public DataIntegrityViolationRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataIntegrityViolationRepositoryException(Throwable cause) {
        super(cause);
    }
    
}
