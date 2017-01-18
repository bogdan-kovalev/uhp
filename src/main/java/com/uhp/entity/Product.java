package com.uhp.entity;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.JsonApiToOne;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bogdan Kovalev.
 */
@Data
@JsonApiResource(type = "products")
public class Product implements Entity {
    @JsonApiId
    private String id;
    private String title;
    private String description;
    private Float cost;

    private List<String> imagesIds = new ArrayList<>();

    @JsonApiToOne
    @DBRef
    private User owner;

    public Product() {
    }

    public Product(String title, Float cost) {
        this.title = title;
        this.cost = cost;
    }
}
