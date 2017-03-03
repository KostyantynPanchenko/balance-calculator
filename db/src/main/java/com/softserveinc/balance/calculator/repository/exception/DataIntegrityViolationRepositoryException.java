package com.softserveinc.balance.calculator.repository.exception;

public class DataIntegrityViolationRepositoryException extends RepositoryException {

    private static final long serialVersionUID = 1L;
    
    public DataIntegrityViolationRepositoryException() { }
    
    public DataIntegrityViolationRepositoryException(String message) {
        super(message);
    }

}
