package com.uhp.repository;

import com.uhp.entity.Master;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Bogdan Kovalev.
 */
public interface MastersRepository extends MongoRepository<Master, String> {
    Master findById(String id);
    Master findByName(String name);
    Master findByEmail(String email);
}
