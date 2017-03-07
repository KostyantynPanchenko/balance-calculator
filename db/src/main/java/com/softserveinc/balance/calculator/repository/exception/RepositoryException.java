package com.softserveinc.balance.calculator.repository.exception;

/**
 * Root of the hierarchy of data access exceptions.
 * 
 * @author Kostyantyn Panchenko
 */
public class RepositoryException extends Exception {

    private static final long serialVersionUID = 1L;
    
    public RepositoryException() { }
    
    public RepositoryException(String message) {
        super(message);
    }
    
    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
