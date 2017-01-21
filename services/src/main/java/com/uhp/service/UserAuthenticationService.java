package com.uhp.service;


import com.uhp.serviceobject.AuthenticatedUser;
import com.uhp.serviceobject.AuthenticationToken;
import com.uhp.serviceobject.LoginCredentials;

/**
 * @author Bogdan Kovalev.
 */

public interface UserAuthenticationService {

    AuthenticationToken auth(LoginCredentials loginCredentials) throws Exception;

    AuthenticationToken refreshToken(AuthenticationToken token) throws Exception;

    AuthenticatedUser retrieveUser(AuthenticationToken token) throws Exception;
}
