package com.uhp.entity;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bogdan Kovalev.
 */
@Data
@JsonApiResource(type = "users")
public class User implements Entity {
    @JsonApiId
    private String id;
    @NonNull
    private String name;
    @NonNull
    private String email;
    private List<Product> products = new ArrayList();
}
