package com.softserveinc.balance.calculator.service.exception;

public class ServiceException extends Exception {

    private static final long serialVersionUID = 1L;
    private String message;
    
    public ServiceException() { }
    
    public ServiceException(String message) {
         this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
