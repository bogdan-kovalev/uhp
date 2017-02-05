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
import io.katharsis.response.JsonApiResponse;
import io.katharsis.response.ResourceResponseContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResourceResponseContext register(@RequestBody io.katharsis.request.dto.RequestBody body) throws Exception {
        final String name = body.getSingleData().getAttributes().findValue("name").asText();
        final String email = body.getSingleData().getAttributes().findValue("email").asText();
        final String password = body.getSingleData().getAttributes().findValue("password").asText();
        final UserRegistrationInfo registrationInfo = new UserRegistrationInfo(new UserName(name), new Email(email), new Password(password));
        final UserDTO userDTO = this.userRegistrationService.register(registrationInfo);
        final JsonApiResponse apiResponse = new JsonApiResponse().setEntity(new User("default", userDTO.name, userDTO.email));
        return new ResourceResponseContext(apiResponse, HttpStatus.OK.value());
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
}
