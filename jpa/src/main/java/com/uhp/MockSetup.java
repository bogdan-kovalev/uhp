package com.uhp;

import com.uhp.entity.Product;
import com.uhp.entity.User;
import com.uhp.repository.ProductRepository;
import com.uhp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author Bogdan Kovalev.
 */
@Service
public class MockSetup {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    @PostConstruct
    void setup() {
        userRepository.deleteAll();
        productRepository.deleteAll();

        final User admin = new User("admin", "Admin", "bogdan.kovalev.job@gmail.com");
        final User marina = new User("Marina", "marina@gmail.com");
        final User anna = new User("Anna", "anna@gmail.com");
        final Product product = productRepository.save(new Product("Flower", 5.11f));

        anna.getProducts().add(product);
        product.setOwner(anna);

        userRepository.save(anna);
        productRepository.save(product);

        userRepository.save(admin);
        userRepository.save(marina);
    }
}
