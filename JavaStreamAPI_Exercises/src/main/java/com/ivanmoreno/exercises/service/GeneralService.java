package com.ivanmoreno.exercises.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivanmoreno.exercises.models.entity.Customer;
import com.ivanmoreno.exercises.models.entity.Order;
import com.ivanmoreno.exercises.models.entity.Product;
import com.ivanmoreno.exercises.models.repository.CustomerRepository;
import com.ivanmoreno.exercises.models.repository.OrderRepository;
import com.ivanmoreno.exercises.models.repository.ProductRepository;

@Service
public class GeneralService {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Transactional(readOnly = true)
	public List<Product> findAllProduct() {
		return productRepo.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Order> findAllOrder() {
		return orderRepo.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Customer> findAllCustomer() {
		return customerRepo.findAll();
	}
}
