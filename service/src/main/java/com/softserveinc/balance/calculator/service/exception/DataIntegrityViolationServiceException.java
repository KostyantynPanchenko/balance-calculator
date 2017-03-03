package com.softserveinc.balance.calculator.service.exception;

public class DataIntegrityViolationServiceException extends ServiceException {

    private static final long serialVersionUID = 1L;

    public DataIntegrityViolationServiceException() { }
    
    public DataIntegrityViolationServiceException(String message) {
        super(message);
    }
}
