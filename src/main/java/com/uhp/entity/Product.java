package com.uhp.entity;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import lombok.Data;

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
}
