package com.ivanmoreno.exercises.models.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ivanmoreno.exercises.models.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	List<Customer> findAll(); 
}
