package com.uhp.repository;

import com.uhp.RepositoryException;
import com.uhp.entity.User;
import io.katharsis.errorhandling.ErrorDataBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
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

    @Override
    public User create(User s) {
        final User byEmail = repository.findByEmail(s.getEmail());
        if (byEmail != null) {
            throw new RepositoryException(
                    HttpStatus.UNPROCESSABLE_ENTITY.value(),
                    new ErrorDataBuilder().setTitle("User with email " + s.getEmail() + " already exists").build()
            );
        }
        return getRepository().save(s);
    }

    @Override
    public void delete(String id) {
        throw new RepositoryException(
                HttpStatus.FORBIDDEN.value(),
                new ErrorDataBuilder().setTitle("Private Operation").build()
        );
    }
}
