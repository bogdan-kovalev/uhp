package com.uhp.service;

import com.uhp.dto.ProductDTO;
import com.uhp.entity.Product;
import com.uhp.exception.EntityNotFoundException;
import com.uhp.exception.NullPropertyException;
import com.uhp.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Bogdan Kovalev
 *         Created on 1/16/17.
 */
@Service
public class ProductsServiceImpl implements ProductsService {
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Override
    public List<ProductDTO> getProducts() {
        return productsRepository.findAll()
                .stream()
                .map(this::toProductDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(String id) {
        return toProductDTO(findOneProduct(id));
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        final Optional<String> title = Optional.ofNullable(productDTO.title);
        final Optional<Float> cost = Optional.ofNullable(productDTO.cost);
        final Optional<String> description = Optional.ofNullable(productDTO.description);
        final Optional<List<String>> imagesIds = Optional.ofNullable(productDTO.imagesIds);

        final Product product = new Product(
                title.orElseThrow(() -> new NullPropertyException("Product title can't be null")),
                cost.orElseThrow(() -> new NullPropertyException("Product cost can't be null")));

        product.setDescription(description.orElse(""));
        product.setImagesIds(imagesIds.orElse(Collections.emptyList()));

        productsRepository.save(product);

        return toProductDTO(product);
    }

    @Override
    public void attachImage(String productId, InputStream stream, String imageFileName) {
        final String imageId = gridFsTemplate.store(stream, imageFileName).getId().toString();
        final Product product = findOneProduct(productId);
        product.getImagesIds().add(imageId);
        productsRepository.save(product);
    }

    private Product findOneProduct(String id) {
        return Optional.ofNullable(productsRepository.findById(id))
                .orElseThrow(() -> new EntityNotFoundException("Product not found - id: " + id));
    }

    private ProductDTO toProductDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getTitle(),
                product.getCost(),
                product.getDescription(),
                product.getImagesIds()
        );
    }
}
