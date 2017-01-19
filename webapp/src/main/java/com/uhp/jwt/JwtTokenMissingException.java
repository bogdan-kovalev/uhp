package com.uhp.jwt;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Bogdan Kovalev.
 */
public class JwtTokenMissingException extends AuthenticationException {
    public JwtTokenMissingException(String msg) {
        super(msg);
    }
}
