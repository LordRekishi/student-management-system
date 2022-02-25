package se.iths.error;

import javax.json.bind.annotation.JsonbTransient;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

public class ErrorMessage {
    private final LocalDateTime localDateTime = LocalDateTime.now();
    private final String errorCode;
    private final String message;
    private final String url;

    public ErrorMessage(String errorCode, String message, String url) {
        this.errorCode = errorCode;
        this.message = message;
        this.url = url;
    }

    @JsonbTransient
    public static Response getByID(Long id) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorMessage("404", "STUDENT with ID " + id + " NOT FOUND!", "/api/v1/students/" + id))
                .type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    @JsonbTransient
    public static Response updateStudent() {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorMessage("404", "STUDENT NOT FOUND!", "/api/v1/students"))
                .type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }

}
