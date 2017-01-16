package com.uhp.service;

import com.uhp.dto.ProductDTO;

import java.io.InputStream;
import java.util.List;

/**
 * @author Bogdan Kovalev
 *         Created on 1/16/17.
 */
public interface ProductsService {
    List<ProductDTO> getProducts();

    ProductDTO getProductById(String id);

    ProductDTO addProduct(ProductDTO productDTO);

    void attachImage(String productId, InputStream stream, String imageFileName);

}
