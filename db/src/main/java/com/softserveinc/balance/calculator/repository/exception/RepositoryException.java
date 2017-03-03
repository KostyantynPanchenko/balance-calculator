package com.softserveinc.balance.calculator.repository.exception;

public class RepositoryException extends Exception {

    private static final long serialVersionUID = 1L;
    private String message;
    
    public RepositoryException() {}
    
    public RepositoryException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
