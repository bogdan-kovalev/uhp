package com.uhp;

import com.uhp.entity.Product;
import com.uhp.entity.User;
import com.uhp.repository.ProductRepository;
import com.uhp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class PrototypeApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(PrototypeApplication.class, args);
    }


    @Override
    public void run(String... strings) throws Exception {
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
