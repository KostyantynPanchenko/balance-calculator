package com.softserveinc.balance.calculator.service.exception;

/**
 * Root of the hierarchy of service exceptions.
 * 
 * @author Kostyantyn Panchenko
 */
public class ServiceException extends Exception {

    private static final long serialVersionUID = 1L;
    
    public ServiceException() { }
    
    public ServiceException(String message) {
         super(message);
    }
    
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
   }

}
