package com.uhp.service;

import com.uhp.dto.UserDTO;

import java.util.List;

/**
 * @author Bogdan Kovalev
 *         Created on 1/16/17.
 */
public interface UsersService {
    List<UserDTO> getUsers();

    UserDTO getUserById(String id);
}
