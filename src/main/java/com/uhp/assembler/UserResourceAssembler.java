package com.uhp.assembler;

import com.uhp.controller.UsersController;
import com.uhp.entity.User;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

/**
 * @author Bogdan Kovalev.
 */
@Component
public class UserResourceAssembler implements ResourceAssembler<User, Resource<User>> {
    @Override
    public Resource<User> toResource(User user) {
        Resource<User> resource = new Resource<>(user);
        resource.add(new Link(UsersController.PATH + "/" + user.getId()).withSelfRel());
        return resource;
    }
}
