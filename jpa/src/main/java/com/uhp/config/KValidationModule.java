package com.uhp.config;

import io.katharsis.validation.ValidationModule;
import io.katharsis.validation.internal.ConstraintViolationExceptionMapper;
import io.katharsis.validation.internal.ValidationExceptionMapper;
import lombok.Getter;

/**
 * @author Bogdan Kovalev.
 */
public class KValidationModule extends ValidationModule {
    @Getter
    private ConstraintViolationExceptionMapper constraintViolationExceptionMapper;
    @Getter
    private ValidationExceptionMapper validationExceptionMapper;

    @Override
    public void setupModule(ModuleContext context) {
        this.constraintViolationExceptionMapper = new ConstraintViolationExceptionMapper(context);
        context.addExceptionMapper(this.constraintViolationExceptionMapper);
        this.validationExceptionMapper = new ValidationExceptionMapper();
        context.addExceptionMapper(this.validationExceptionMapper);
    }
}
