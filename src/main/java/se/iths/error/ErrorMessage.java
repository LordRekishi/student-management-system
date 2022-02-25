package se.iths.error;

import se.iths.entity.Student;

import javax.json.bind.annotation.JsonbTransient;
import javax.validation.ConstraintViolation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ErrorMessage {
    private final LocalDateTime localDateTime = LocalDateTime.now();
    private final String errorCode;
    private String message;
    private List<String> messages;
    private final String url;

    public ErrorMessage(String errorCode, String message, String url) {
        this.errorCode = errorCode;
        this.message = message;
        this.url = url;
    }

    public ErrorMessage(String errorCode, List<String> messages, String url) {
        this.errorCode = errorCode;
        this.messages = messages;
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

    @JsonbTransient
    public static Response createStudent(Set<ConstraintViolation<Student>> violations) {
        List<String> messages = new ArrayList<>();
        for (ConstraintViolation<Student> violation : violations) {
            messages.add(violation.getMessage());
        }

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorMessage("400", messages, "/api/v1/students"))
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

    public List<String> getMessages() {
        return messages;
    }

    public String getUrl() {
        return url;
    }

}
