package com.softserveinc.balance.calculator.repository.exception;

/**
 * Data access exception thrown when a result was expected to have at least 
 * one row (or element) but zero rows (or elements) were actually returned.
 * 
 * @author Kostyantyn Panchenko
 */
public class DomainEntityNotFoundException extends RepositoryException {
    
    private static final long serialVersionUID = 1L;

    public DomainEntityNotFoundException() { }
    
    public DomainEntityNotFoundException(String message) {
        super(message);
    }
    
    public DomainEntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DomainEntityNotFoundException(Throwable cause) {
        super(cause);
    }
    
}
