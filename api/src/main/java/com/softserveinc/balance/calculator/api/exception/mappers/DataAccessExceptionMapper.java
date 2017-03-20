package com.softserveinc.balance.calculator.api.exception.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.dao.DataAccessException;

@Provider
public class DataAccessExceptionMapper implements ExceptionMapper<DataAccessException> {

    @Override
    public Response toResponse(DataAccessException exception) {
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error").build();
    }

}
