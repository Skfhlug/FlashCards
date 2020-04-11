package edu.team.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFound extends Exception implements ExceptionMapper<NotFound> {
    private static final long serialVersionUID = 1L;

    public NotFound() {
        super("No result found!");
    }

    public NotFound(String string) {
        super(string);
    }

    @Override
    public Response toResponse(NotFound exception)
    {
        return Response.status(404).entity(exception.getMessage())
                .type("text/plain").build();
    }
}