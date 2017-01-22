package com.uhp.controller;

import io.katharsis.errorhandling.ErrorData;
import io.katharsis.errorhandling.ErrorResponse;
import io.katharsis.errorhandling.exception.KatharsisMappableException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static java.util.Collections.singletonList;

/**
 * @author Bogdan Kovalev.
 */
@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(KatharsisMappableException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleException(KatharsisMappableException ex) {
        final ErrorData errorData = ex.getErrorData();
        final int status = ex.getHttpStatus();

        return ResponseEntity
                .status(status)
                .body(new ErrorResponse(singletonList(errorData), status));
    }
}
