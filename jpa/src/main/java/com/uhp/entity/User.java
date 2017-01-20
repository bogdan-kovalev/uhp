package com.uhp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.JsonApiToMany;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

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
    private String name;
    private String email;

    @JsonIgnore
    private String passwordHash;
    @JsonIgnore
    private byte[] passwordSalt;

    public User(String name, String email, String passwordHash, byte[] passwordSalt) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
    }

    @JsonApiToMany
    @DBRef
    private List<Product> products = new ArrayList<>();

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
