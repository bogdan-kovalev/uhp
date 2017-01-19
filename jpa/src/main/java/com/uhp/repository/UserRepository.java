package com.uhp.repository;

import com.uhp.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bogdan Kovalev.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    public User findByEmail(String email);
}
