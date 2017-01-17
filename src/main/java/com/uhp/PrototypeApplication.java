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

        final User admin = new User("Admin", "bogdan.kovalev.job@gmail.com");
        admin.setId("admin");
        userRepository.save(admin);

        final Product product = new Product();
        product.setTitle("Flower");
        product.setCost(5.11f);
        productRepository.save(product);

        userRepository.save(new User("Marina", "marina@gmail.com"));
        final User anna = new User("Anna", "anna@gmail.com");
        anna.getProducts().add(product);
        userRepository.save(anna);
    }
}
