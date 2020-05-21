package com.imdb.movie.web.rest.errors;

import com.imdb.movie.exception.NameNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * A controller advice to translate the server side exceptions to user friendly format.
 *
 * @author gbhat on 14/05/2020.
 */
@ControllerAdvice
@Slf4j
public class ExceptionTranslator {
    /**
     * Exception handler for all the exceptions.
     *
     * @param ex the exception.
     * @return the response entity with api error response.
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiErrorResponse> handleAllExceptions(Exception ex) {
        log.error("Error::", ex);
        ApiErrorResponse errors = new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Exception handler for name not found exception.
     *
     * @param ex the name not found exception.
     * @return the response entity with api error response.
     */
    @ExceptionHandler(NameNotFoundException.class)
    public final ResponseEntity<ApiErrorResponse> handleNameNotFoundException(NameNotFoundException ex) {
        log.error("Error::", ex);
        ApiErrorResponse errors = new ApiErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }
}
