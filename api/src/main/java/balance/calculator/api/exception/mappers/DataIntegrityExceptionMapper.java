package balance.calculator.api.exception.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import io.dropwizard.jersey.errors.ErrorMessage;

@Component
@Provider
public class DataIntegrityExceptionMapper implements ExceptionMapper<DataIntegrityViolationException> {

    @Override
    public Response toResponse(DataIntegrityViolationException exception) {
        return Response.status(Status.CONFLICT).entity(new ErrorMessage(Status.CONFLICT.getStatusCode(), "Database constraint violation.")).build();
    }

}
