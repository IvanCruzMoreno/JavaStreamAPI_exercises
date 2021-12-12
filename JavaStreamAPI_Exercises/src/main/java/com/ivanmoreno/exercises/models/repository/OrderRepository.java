package com.ivanmoreno.exercises.models.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ivanmoreno.exercises.models.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

	List<Order> findAll(); 
}
