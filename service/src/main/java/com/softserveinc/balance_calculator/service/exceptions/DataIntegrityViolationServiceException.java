package com.softserveinc.balance_calculator.service.exceptions;

/**
 * Exception thrown when an attempt to insert or update data results in violation of an integrity constraint.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 06/03/2017
 */
public class DataIntegrityViolationServiceException extends ServiceException {

    private static final long serialVersionUID = 1L;

    public DataIntegrityViolationServiceException() { }
    
    public DataIntegrityViolationServiceException(String message) {
        super(message);
    }
    
    public DataIntegrityViolationServiceException(Throwable cause) {
        super( cause);
    }
    
    public DataIntegrityViolationServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
