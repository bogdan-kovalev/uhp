package com.uhp.serviceobject;

import com.uhp.tinytypes.Email;
import com.uhp.tinytypes.Password;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author Bogdan Kovalev.
 */
@ToString
@RequiredArgsConstructor
public class LoginCredentials {
    public final Email email;
    public final Password password;
}
