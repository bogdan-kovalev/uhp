package com.uhp.entity;

import lombok.Data;
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
    private String title;
    private String description;
    private Float cost;

    private List<String> imagesIds = new ArrayList<>();

    private final String type = "product";
}
