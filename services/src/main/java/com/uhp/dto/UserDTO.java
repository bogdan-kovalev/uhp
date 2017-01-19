package com.uhp.dto;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author Bogdan Kovalev.
 */
@RequiredArgsConstructor
@ToString
public class UserDTO {

    public final String id;
    public final String name;
    public final String email;
}
