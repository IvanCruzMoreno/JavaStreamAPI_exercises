package com.ivanmoreno.exercises.models.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ivanmoreno.exercises.models.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

	public List<Product> findAll(); 
}
