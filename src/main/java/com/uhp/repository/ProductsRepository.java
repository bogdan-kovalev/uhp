package com.uhp.repository;

import com.uhp.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bogdan Kovalev.
 */
@Repository
public interface ProductsRepository extends MongoRepository<Product, String> {
    Product findById(String id);
}
