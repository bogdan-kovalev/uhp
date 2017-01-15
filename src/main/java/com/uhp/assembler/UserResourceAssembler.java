package com.uhp.assembler;

import com.uhp.controller.UsersController;
import com.uhp.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author Bogdan Kovalev.
 */
@Component
public class UserResourceAssembler extends AbstractResourceAssembler<User> {
    @Override
    String getPath() {
        return UsersController.PATH;
    }
}
