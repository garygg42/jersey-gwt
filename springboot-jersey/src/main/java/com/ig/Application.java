package com.ig;

import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.ig.config.JerseyConfig;
import com.ig.domain.Customer;
import com.ig.repository.CustomerRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	CustomerRepository repository;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class);
	}

	@Bean
	public ServletRegistrationBean jerseyServlet() {
		ServletRegistrationBean registration = new ServletRegistrationBean(
				new ServletContainer(), "/rest/*");
		registration.addInitParameter(
				ServletProperties.JAXRS_APPLICATION_CLASS,
				JerseyConfig.class.getName());
		return registration;
	}

	@Override
	public void run(String... strings) throws Exception {
		// populate h2 database
		repository.save(new Customer("Jack", "Bauer"));
		repository.save(new Customer("Chloe", "O'Brian"));
		repository.save(new Customer("Kim", "Bauer"));
		repository.save(new Customer("David", "Palmer"));
		repository.save(new Customer("Michelle", "Dessler"));
	}
}
