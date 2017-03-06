package com.softserveinc.balance.calculator.service.exception;

/**
 * Service exception thrown when requested entity was not return (was 
 * not found) from data access layer.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 06/03/2017
 */
public class EntityNotFoundServiceException extends ServiceException {

    private static final long serialVersionUID = 1L;
    
    public EntityNotFoundServiceException(String message) {
        super(message);
    }

}
