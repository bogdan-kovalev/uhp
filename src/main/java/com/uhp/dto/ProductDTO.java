package com.uhp.dto;

import lombok.AllArgsConstructor;

import java.util.List;

/**
 * @author Bogdan Kovalev
 *         Created on 1/16/17.
 */
@AllArgsConstructor
public class ProductDTO {
    public String id;
    public String title;
    public Float cost;
    public String description;
    public List<String> imagesIds;
}
