package com.uhp.service;

import com.uhp.entity.Product;

import java.util.List;

/**
 * @author Bogdan Kovalev
 *         Created on 1/16/17.
 */
public interface ProductsService {
    List<Product> getProducts();

    Product getProductById(String id);

    Product addProduct(ProductDTO productDTO);

    public static class ProductDTO {

    }
}
