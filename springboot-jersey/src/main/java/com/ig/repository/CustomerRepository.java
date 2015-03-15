package com.ig.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ig.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	List<Customer> findByLastName(String lastName);
}