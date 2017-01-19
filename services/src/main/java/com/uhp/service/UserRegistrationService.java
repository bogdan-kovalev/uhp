package com.uhp.service;


import com.uhp.dto.UserDTO;
import com.uhp.serviceobject.UserRegistrationInfo;

/**
 * @author Bogdan Kovalev.
 */
public interface UserRegistrationService {

    UserDTO register(UserRegistrationInfo registrationInfo) throws Exception;

}
