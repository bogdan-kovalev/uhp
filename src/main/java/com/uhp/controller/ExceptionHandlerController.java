package com.uhp.controller;

import com.uhp.exception.EntityNotFoundException;
import com.uhp.exception.NullPropertyException;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bogdan Kovalev.
 */
@ControllerAdvice
public class ExceptionHandlerController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<ErrorResponse> entityNotFoundException(Exception e) {
        logger.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(value = {NullPropertyException.class})
    public ResponseEntity<ErrorResponse> nullPropertyException(Exception e) {
        logger.error(e.getMessage());
        return ResponseEntity
                .unprocessableEntity()
                .body(new ErrorResponse(e.getMessage()));
    }

    @Data
    private static class ErrorResponse {
        List<String> errors = new ArrayList<>();

        ErrorResponse(String msg) {
            errors.add(msg);
        }
    }
}
