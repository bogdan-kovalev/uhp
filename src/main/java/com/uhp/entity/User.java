package com.uhp.entity;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bogdan Kovalev.
 */
@Data
public class User implements Entity {
    @Id
    private String id;
    @NonNull
    private String name;
    @NonNull
    private String email;
    private List<Product> products = new ArrayList();

    private final String type = "user";
}
