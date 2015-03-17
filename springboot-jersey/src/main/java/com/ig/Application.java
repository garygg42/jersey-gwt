package com.ig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;

import com.ig.domain.Customer;
import com.ig.repository.CustomerRepository;

@SpringBootApplication(exclude = { ErrorMvcAutoConfiguration.class })
public class Application implements CommandLineRunner {

	@Autowired
	CustomerRepository repository;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class);
	}

	/**
	 * Populate embedded h2 database after application start.
	 */
	@Override
	public void run(String... strings) throws Exception {
		repository.save(new Customer("Jack", "Bauer"));
		repository.save(new Customer("Jack", "Bauer"));
		repository.save(new Customer("Chloe", "O'Brian"));
		repository.save(new Customer("Kim", "Bauer"));
		repository.save(new Customer("David", "Palmer"));
		repository.save(new Customer("Michelle", "Dessler"));
	}
}
