package com.uhp.repository;

import com.uhp.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

/**
 * @author Bogdan Kovalev.
 */
@Component
public class ProductResourceRepository extends AbstractResourceRepository<Product, String> {

    @Autowired
    private ProductRepository repository;


    @Override
    MongoRepository<Product, String> getRepository() {
        return repository;
    }

    @Override
    public Class<Product> getResourceClass() {
        return Product.class;
    }
}
