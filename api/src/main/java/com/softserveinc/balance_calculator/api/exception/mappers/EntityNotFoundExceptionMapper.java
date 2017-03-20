package com.softserveinc.balance_calculator.api.exception.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.dao.EmptyResultDataAccessException;

import io.dropwizard.jersey.errors.ErrorMessage;

@Provider
public class EntityNotFoundExceptionMapper implements ExceptionMapper<EmptyResultDataAccessException> {

    @Override
    public Response toResponse(EmptyResultDataAccessException exception) {
        return Response.status(Status.NOT_FOUND).entity(new ErrorMessage(404, exception.getMessage())).build();
    }

}
