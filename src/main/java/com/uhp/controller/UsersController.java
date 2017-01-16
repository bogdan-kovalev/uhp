package com.uhp.controller;

import com.uhp.assembler.UserResourceAssembler;
import com.uhp.entity.User;
import com.uhp.exception.EntityNotFoundException;
import com.uhp.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Bogdan Kovalev.
 */
@RestController
@RequestMapping(UsersController.PATH)
public class UsersController {
    public static final String PATH = "api/users";

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserResourceAssembler userResourceAssembler;

    @GetMapping
    ResponseEntity<List<Resource<User>>> getUsers() {
        return ResponseEntity.ok(usersRepository.findAll()
                .stream()
                .map(userResourceAssembler::toResource)
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Resource<User>> getUserById(@PathVariable("id") String id) {
        final User user = Optional.ofNullable(usersRepository.findById(id))
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        final Resource<User> userResource = userResourceAssembler.toResource(user);
        return ResponseEntity.ok(userResource);
    }
}
