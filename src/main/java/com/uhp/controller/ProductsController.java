package com.uhp.controller;

import com.uhp.dto.ProductDTO;
import com.uhp.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/{id}/addimage")
    void uploadProductImage(@PathVariable("id") String productId, @RequestParam("file") MultipartFile file) {
        try {
            productsService.attachImage(productId, file.getInputStream(), file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping
    ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO addProductRequest) {
        final ProductDTO productDTO = productsService.addProduct(addProductRequest);
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
