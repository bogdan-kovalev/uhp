package com.uhp.serviceobject;

import com.uhp.tinytypes.Email;
import com.uhp.tinytypes.Password;
import com.uhp.tinytypes.UserName;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author Bogdan Kovalev.
 */

@RequiredArgsConstructor
@ToString
public class UserRegistrationInfo {

    public final UserName name;
    public final Email email;
    public final Password password;
}
