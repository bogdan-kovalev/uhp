package com.uhp.controller;

import com.uhp.assembler.ProductResourceAssembler;
import com.uhp.entity.Product;
import com.uhp.exception.EntityNotFoundException;
import com.uhp.exception.NullPropertyException;
import com.uhp.repository.ProductsRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Bogdan Kovalev.
 */
@RestController
@RequestMapping(ProductsController.PATH)
public class ProductsController {
    public static final String PATH = "api/products";

    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private ProductResourceAssembler productResourceAssembler;

    @PostMapping
    ResponseEntity<Resource<Product>> addProduct(@RequestBody AddProductRequest addProductRequest) {
        final Optional<String> title = Optional.ofNullable(addProductRequest.title);
        final Optional<Float> cost = Optional.ofNullable(addProductRequest.cost);
        final Optional<String> description = Optional.ofNullable(addProductRequest.description);
        final Optional<List<String>> imagesIds = Optional.ofNullable(addProductRequest.imagesIds);

        final Product product = new Product(
                title.orElseThrow(() -> new NullPropertyException("Product title can't be null")),
                cost.orElseThrow(() -> new NullPropertyException("Product cost can't be null")));

        product.setDescription(description.orElse(""));
        product.setImagesIds(imagesIds.orElse(Collections.emptyList()));

        productsRepository.save(product);

        final Resource<Product> productResource = productResourceAssembler.toResource(product);
        return ResponseEntity.ok(productResource);
    }

    @GetMapping
    ResponseEntity<List<Resource<Product>>> getProducts() {
        return ResponseEntity.ok(productsRepository.findAll()
                .stream()
                .map(productResourceAssembler::toResource)
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Resource<Product>> getProductById(@PathVariable("id") String id) {
        final Product product = Optional.ofNullable(productsRepository.findById(id))
                .orElseThrow(() -> new EntityNotFoundException("Product not found - id: " + id));
        final Resource<Product> productResource = productResourceAssembler.toResource(product);
        return ResponseEntity.ok(productResource);
    }

    @Data
    private static class AddProductRequest {
        String title;
        String description;
        Float cost;
        List<String> imagesIds;
    }
}
