package com.uhp.repository;

import com.uhp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

/**
 * @author Bogdan Kovalev.
 */
@Component
public class UserResourceRepository extends AbstractResourceRepository<User, String> {

    @Autowired
    private UserRepository repository;

    @Override
    MongoRepository<User, String> getRepository() {
        return repository;
    }

    @Override
    public Class<User> getResourceClass() {
        return User.class;
    }
}
