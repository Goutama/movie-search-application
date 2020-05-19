package com.imdb.movie.web.rest.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * A ApiErrorResponse.
 *
 * @author gbhat on 14/05/2020.
 */
@Data
class ApiErrorResponse {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;

    private ApiErrorResponse() {
        timestamp = LocalDateTime.now();
    }

    ApiErrorResponse(HttpStatus status) {
        this();
        this.status = status;
    }

    ApiErrorResponse(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
    }

    ApiErrorResponse(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }
}
