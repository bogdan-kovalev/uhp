package com.uhp.controller;

import com.uhp.config.KValidationModule;
import io.katharsis.errorhandling.ErrorData;
import io.katharsis.errorhandling.ErrorResponse;
import io.katharsis.errorhandling.exception.KatharsisMappableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

/**
 * @author Bogdan Kovalev.
 */
@ControllerAdvice
public class ExceptionsHandler {
    @Autowired
    private KValidationModule validationModule;


    @ExceptionHandler(KatharsisMappableException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleException(KatharsisMappableException ex) {
        final ErrorData errorData = ex.getErrorData();
        final int status = ex.getHttpStatus();
        return ResponseEntity.status(status).body(ErrorResponse.builder().setStatus(status).setSingleErrorData(errorData).build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleException(ConstraintViolationException ex) {
        final ErrorResponse errorResponse = validationModule.getConstraintViolationExceptionMapper().toErrorResponse(ex);
        return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponse);
    }
}
