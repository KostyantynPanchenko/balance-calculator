package com.softserveinc.balance.calculator.service.exception;

/**
 * Root of the hierarchy of service exceptions.
 * 
 * @author Kostyantyn Panchenko
 */
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
