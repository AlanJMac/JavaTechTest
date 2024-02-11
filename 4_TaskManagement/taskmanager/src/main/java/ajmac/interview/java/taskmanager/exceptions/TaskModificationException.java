package ajmac.interview.java.taskmanager.exceptions;

import org.springframework.http.HttpStatus;

public class TaskModificationException extends RuntimeException {

    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    public TaskModificationException(HttpStatus httpStatus, String message) {
        super(message);

        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
