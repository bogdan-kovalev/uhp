package com.uhp.controller;

import com.uhp.dto.UserDTO;
import com.uhp.entity.User;
import com.uhp.repository.UserRepository;
import com.uhp.service.UserAuthenticationService;
import com.uhp.service.UserRegistrationService;
import com.uhp.serviceobject.AuthenticatedUser;
import com.uhp.serviceobject.AuthenticationToken;
import com.uhp.serviceobject.LoginCredentials;
import com.uhp.serviceobject.UserRegistrationInfo;
import com.uhp.tinytypes.Email;
import com.uhp.tinytypes.Password;
import com.uhp.tinytypes.UserName;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * @author Bogdan Kovalev.
 */
@RestController
@RequestMapping("/api/user")
public class UserServiceController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @Autowired
    private UserRepository userRepository;

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
    @RequestMapping("/auth")
    public AuthenticationToken auth(@RequestBody LoginRequestBody body) throws Exception {
        return this.userAuthenticationService.auth(
                new LoginCredentials(
                        new Email(body.email), new Password(body.password)
                )
        );
    }

    @PostMapping
    @RequestMapping("/refresh-token")
    public AuthenticationToken refreshToken(@RequestBody AuthenticationToken token) throws Exception {
        return this.userAuthenticationService.refreshToken(token);
    }

    @GetMapping(value = "/current")
    public UserDTO getCurrentUser(@AuthenticationPrincipal AuthenticatedUser authenticatedUser) {
        final String id = authenticatedUser.getId();
        final User currentUser = userRepository.findOne(id);
        return new UserDTO(id, currentUser.getName(), currentUser.getEmail());
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
