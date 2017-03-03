package com.softserveinc.balance.calculator.repository.exception;

public class DomainEntityNotFoundException extends RepositoryException {
    
    private static final long serialVersionUID = 1L;

    public DomainEntityNotFoundException() { }
    
    public DomainEntityNotFoundException(String message) {
        super(message);
    }
}
