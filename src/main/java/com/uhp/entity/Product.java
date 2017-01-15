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
public class Product implements Entity {
    @Id
    private String id;
    @NonNull
    private String title;
    private String description;

    @NonNull
    private Float cost;

    private List<String> imagesIds = new ArrayList<>();

    private final String type = "product";
}
