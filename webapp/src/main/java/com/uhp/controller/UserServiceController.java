package com.uhp.controller;

import com.uhp.dto.UserDTO;
import com.uhp.service.UserAuthenticationService;
import com.uhp.service.UserRegistrationService;
import com.uhp.serviceobject.AuthenticationToken;
import com.uhp.serviceobject.LoginCredentials;
import com.uhp.serviceobject.UserRegistrationInfo;
import com.uhp.tinytypes.Email;
import com.uhp.tinytypes.Password;
import com.uhp.tinytypes.UserName;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Bogdan Kovalev.
 */
@RestController
@RequestMapping("/api")
public class UserServiceController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @PostMapping
    @RequestMapping("/register")
    public UserDTO register(@RequestBody RegistrationRequestBody body) throws Exception {
        return this.userRegistrationService.register(
                new UserRegistrationInfo(
                        new UserName(body.name), new Email(body.email), new Password(body.password)
                )
        );
    }

    @PostMapping
    @RequestMapping("/login")
    public AuthenticationToken login(@RequestBody LoginRequestBody body) throws Exception {
        return this.userAuthenticationService.login(
                new LoginCredentials(
                        new Email(body.email), new Password(body.password)
                )
        );
    }

    @GetMapping
    @RequestMapping("/users")
    public String getUsers() throws Exception {
        return "Users!";
    }

    @RequiredArgsConstructor
    static class LoginRequestBody {
        final String email;
        final String password;
    }

    @RequiredArgsConstructor
    static class RegistrationRequestBody {
        final String name;
        final String email;
        final String password;
    }
}
