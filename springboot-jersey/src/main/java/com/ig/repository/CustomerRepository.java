package com.ig.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ig.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	List<Customer> findByFirstNameIgnoreCase(String firstName);

	List<Customer> findByLastNameIgnoreCase(String lastName);

	List<Customer> findByFirstNameAndLastNameAllIgnoreCase(String firstName,
			String lastName);
}