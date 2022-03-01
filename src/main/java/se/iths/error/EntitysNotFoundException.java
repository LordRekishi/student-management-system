package se.iths.error;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class EntitysNotFoundException extends WebApplicationException {

    public EntitysNotFoundException(Response response) {
        super((Throwable) null, response);
    }
}
