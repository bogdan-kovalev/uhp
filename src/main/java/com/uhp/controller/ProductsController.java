package com.uhp.controller;

import com.uhp.assembler.ProductResourceAssembler;
import com.uhp.entity.Product;
import com.uhp.exception.EntityNotFoundException;
import com.uhp.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
