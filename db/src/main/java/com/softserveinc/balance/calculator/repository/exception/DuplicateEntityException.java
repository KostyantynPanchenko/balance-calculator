package com.softserveinc.balance.calculator.repository.exception;

public class DuplicateEntityException extends RepositoryException {

    private static final long serialVersionUID = 1L;
    
    public DuplicateEntityException() { }
    
    public DuplicateEntityException(String message) {
        super(message);
    }

}
