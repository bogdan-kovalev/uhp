package com.uhp.entity;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

/**
 * @author Bogdan Kovalev.
 */
@Data
public class User {
    @Id
    private String id;
    @NonNull
    private String name;
    @NonNull
    private String email;

    private final String type = "user";
}
