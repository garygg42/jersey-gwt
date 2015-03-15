package com.ig.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ig.domain.Customer;
import com.ig.exception.ExceptionInfo;
import com.ig.repository.CustomerRepository;

@Path("/customers")
@Component
public class CustomerResource {

	@Autowired
	CustomerRepository repository;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Iterable<Customer> getCustomers() {

		Iterable<Customer> customers = repository.findAll();
		return customers;
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findById(@PathParam("id") Long id) {

		Customer customer = repository.findOne(id);
		if (customer != null) {
			return Response.status(Status.OK).entity(customer).build();
		} else {
			return Response
					.status(Status.NOT_FOUND)
					.entity(new ExceptionInfo(Status.NOT_FOUND.getStatusCode(),
							"Not found.", "Customer with id " + id
									+ " does not exist.")).build();
		}
	}

	@GET
	@Path("findByLastName/{lastName}")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Customer> findByLastName(@PathParam("lastName") String lastName) {

		List<Customer> customers = repository.findByLastName(lastName);
		return customers;
	}
}
