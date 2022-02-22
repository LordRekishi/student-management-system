package se.iths.error;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class StudentsNotFoundException extends WebApplicationException {

    public StudentsNotFoundException(Response response) {
        super((Throwable) null, response);
    }
}
