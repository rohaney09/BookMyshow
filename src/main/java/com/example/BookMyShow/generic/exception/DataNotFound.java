package com.example.BookMyShow.generic.exception;



/**
 * Custom runtime exception used for business validation failures.
 */
public class DataNotFound extends RuntimeException {

    public DataNotFound(String message) {
        super(message);
    }
}
