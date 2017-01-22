package com.uhp;

import io.katharsis.errorhandling.ErrorData;
import io.katharsis.errorhandling.exception.KatharsisMappableException;

/**
 * @author Bogdan Kovalev.
 */
public class RepositoryException extends KatharsisMappableException {

    public RepositoryException(int httpStatus, ErrorData errorData) {
        super(httpStatus, errorData);
    }
}
