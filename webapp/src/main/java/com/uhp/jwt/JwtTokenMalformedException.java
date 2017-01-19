package com.uhp.jwt;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Bogdan Kovalev.
 */
public class JwtTokenMalformedException extends AuthenticationException {

    public JwtTokenMalformedException(String msg) {
        super(msg);
    }
}
