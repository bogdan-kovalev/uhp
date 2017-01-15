package com.uhp.repository;

import com.uhp.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Bogdan Kovalev.
 */
public interface UsersRepository extends MongoRepository<User, String> {
    User findById(String id);

    User findByName(String name);

    User findByEmail(String email);
}
