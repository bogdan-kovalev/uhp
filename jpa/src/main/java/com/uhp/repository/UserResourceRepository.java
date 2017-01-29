package com.uhp.repository;

import com.uhp.RepositoryException;
import com.uhp.entity.User;
import io.katharsis.errorhandling.ErrorDataBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author Bogdan Kovalev.
 */
@Component
public class UserResourceRepository extends AbstractResourceRepository<User, String> {

    @Autowired
    private UserRepository repository;

    @Autowired
    private Validator validator;

    @Override
    MongoRepository<User, String> getRepository() {
        return repository;
    }

    @Override
    public Class<User> getResourceClass() {
        return User.class;
    }

    @Override
    public User create(User user) {
        final Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        final User byEmail = repository.findByEmail(user.getEmail());
        if (byEmail != null) {
            throw new RepositoryException(
                    HttpStatus.UNPROCESSABLE_ENTITY.value(),
                    new ErrorDataBuilder().setTitle("User with email " + user.getEmail() + " already exists").build()
            );
        }
        return getRepository().save(user);
    }

    @Override
    public void delete(String id) {
        throw new RepositoryException(
                HttpStatus.FORBIDDEN.value(),
                new ErrorDataBuilder().setTitle("Private Operation").build()
        );
    }
}
