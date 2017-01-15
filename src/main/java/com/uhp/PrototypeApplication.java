package com.uhp;

import com.uhp.entity.User;
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
	private UsersRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(PrototypeApplication.class, args);
	}


	@Override
	public void run(String... strings) throws Exception {
		repository.deleteAll();

		repository.save(new User("Marina", "marina@gmail.com"));
		repository.save(new User("Anna", "anna@gmail.com"));
	}
}
