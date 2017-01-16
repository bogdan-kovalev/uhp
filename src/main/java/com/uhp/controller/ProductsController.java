package com.uhp.controller;

import com.uhp.dto.ProductDTO;
import com.uhp.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Bogdan Kovalev.
 */
@RestController
@RequestMapping(ProductsController.PATH)
public class ProductsController {
    public static final String PATH = "api/products";

    @Autowired
    private ProductsService productsService;

    @PutMapping
    ResponseEntity<ProductDTO> putProduct(@RequestBody ProductDTO putProductRequest) {
        final ProductDTO productDTO = productsService.editProduct(putProductRequest);
        return ResponseEntity.ok(productDTO);
    }

    @PostMapping
    ResponseEntity<ProductDTO> postProduct(@RequestBody ProductDTO postProductRequest) {
        final ProductDTO productDTO = productsService.addProduct(postProductRequest);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping
    ResponseEntity<List<ProductDTO>> getProducts() {
        return ResponseEntity.ok(productsService.getProducts());
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<ProductDTO> getProductById(@PathVariable("id") String id) {
        return ResponseEntity.ok(productsService.getProductById(id));
    }
}
