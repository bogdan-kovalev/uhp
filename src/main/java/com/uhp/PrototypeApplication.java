package com.uhp;

import com.uhp.entity.Master;
import com.uhp.repository.MastersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PrototypeApplication implements CommandLineRunner {

	@Autowired
	private MastersRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(PrototypeApplication.class, args);
	}


	@Override
	public void run(String... strings) throws Exception {
		repository.deleteAll();

		repository.save(new Master("Marina", "marina@gmail.com"));
		repository.save(new Master("Anna", "anna@gmail.com"));
	}
}
