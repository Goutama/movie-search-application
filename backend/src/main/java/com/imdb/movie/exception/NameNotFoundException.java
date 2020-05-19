package com.imdb.movie.exception;

/**
 * Exception to throw when actor/actress name not found in database.
 *
 * @author gbhat on 19/05/2020.
 */
public class NameNotFoundException extends Exception {
    public NameNotFoundException(String message) {
        super(message);
    }
}
