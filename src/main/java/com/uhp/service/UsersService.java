package com.uhp.service;

import com.uhp.entity.User;

import java.util.List;

/**
 * @author Bogdan Kovalev
 *         Created on 1/16/17.
 */
public interface UsersService {
    List<User> getUsers();

    User getUserById(String id);
}
