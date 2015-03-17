package com.ig.rest;

import java.util.Collections;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ig.domain.Customer;
import com.ig.repository.CustomerRepository;

@Path("customers")
@Component
public class CustomerResource {

	@Autowired
	CustomerRepository repository;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Iterable<Customer> findCustomers(
			@QueryParam("firstName") String firstName,
			@QueryParam("lastName") String lastName) {

		Iterable<Customer> customers = Collections.emptyList();
		if (StringUtils.isEmpty(firstName) && StringUtils.isEmpty(lastName))
			customers = repository.findAll();
		else if (!StringUtils.isEmpty(firstName)
				&& !StringUtils.isEmpty(lastName))
			customers = repository.findByFirstNameAndLastNameAllIgnoreCase(
					firstName, lastName);
		else if (!StringUtils.isEmpty(firstName))
			customers = repository.findByFirstNameIgnoreCase(firstName);
		else if (!StringUtils.isEmpty(lastName))
			customers = repository.findByLastNameIgnoreCase(lastName);
		return customers;
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Customer findById(@PathParam("id") Long id) {

		Customer customer = repository.findOne(id);
		if (customer != null) {
			throw new NotFoundException(String.format(
					"Customer with id: %d does not exist.", id));
		}
		return customer;
	}

}
