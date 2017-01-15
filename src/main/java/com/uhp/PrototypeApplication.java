package com.uhp;

import com.uhp.entity.Product;
import com.uhp.entity.User;
import com.uhp.repository.ProductsRepository;
import com.uhp.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class PrototypeApplication implements CommandLineRunner {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    ProductsRepository productsRepository;

    public static void main(String[] args) {
        SpringApplication.run(PrototypeApplication.class, args);
    }


    @Override
    public void run(String... strings) throws Exception {
        usersRepository.deleteAll();
        productsRepository.deleteAll();

        final Product flower = new Product("Flower", 0.5f);
        productsRepository.save(flower);

        usersRepository.save(new User("Marina", "marina@gmail.com"));
        final User anna = new User("Anna", "anna@gmail.com");
        anna.getProducts().add(flower);
        usersRepository.save(anna);
    }
}
