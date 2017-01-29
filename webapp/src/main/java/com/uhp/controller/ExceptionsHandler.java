package com.uhp.controller;

import com.uhp.config.KValidationModule;
import io.katharsis.errorhandling.ErrorData;
import io.katharsis.errorhandling.ErrorResponse;
import io.katharsis.errorhandling.exception.KatharsisMappableException;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ErrorResponse handleException(KatharsisMappableException ex) {
        final ErrorData errorData = ex.getErrorData();
        final int status = ex.getHttpStatus();
        return ErrorResponse.builder().setStatus(status).setSingleErrorData(errorData).build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ErrorResponse handleException(ConstraintViolationException ex) {
        return validationModule.getConstraintViolationExceptionMapper().toErrorResponse(ex);
    }
}
