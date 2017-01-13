package com.uhp.exception;

/**
 * @author Bogdan Kovalev.
 */
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
