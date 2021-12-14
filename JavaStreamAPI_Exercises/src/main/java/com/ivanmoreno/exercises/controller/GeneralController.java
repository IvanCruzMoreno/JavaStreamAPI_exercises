package com.ivanmoreno.exercises.controller;

import java.util.List;
import static java.util.stream.Collectors.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivanmoreno.exercises.models.entity.Order;
import com.ivanmoreno.exercises.models.entity.Product;
import com.ivanmoreno.exercises.service.GeneralService;

@RestController
@RequestMapping("/api")
public class GeneralController {

	@Autowired
	private GeneralService service;
	
	@GetMapping("/e1")
	public List<Product> exercise1() {
		//Obtain a list of product with category Books and price > 100
		
		return service.findAllProduct()
				      .stream()
				      .filter(product -> product.getCategory().equalsIgnoreCase("books"))
				      .filter(book -> book.getPrice() > 100)
				      .collect(toList());
	}
	
	@GetMapping("/e2")
	public List<Order> exercise2() {
		//Obtain a list of order with products belong to category "Baby"
		
		List<Order> orders = service.findAllOrder()
				      .stream()
				      .filter(order -> 
				        order.getProducts()
				    	.stream()
				    	.anyMatch(product -> product.getCategory().equalsIgnoreCase("Baby")))
				      .collect(toList());
		
	    return orders;
	}
	
	@GetMapping("/e3")
	public List<Product> exercise3() {
		//Obtain a list of products with category = "toys" and then apply 10% discount 
		
		List<Product> products = service.findAllProduct()
				.stream()
				.filter(product -> product.getCategory().equalsIgnoreCase("toys"))
				.map(product -> product.withPrice(product.getPrice() * (.10)))
				.collect(toList());
				
	    return products;
	}
	
	@GetMapping("/e4")
	public List<Product> exercise4() {
		//Obtain a list of products ordered by customer of tier 2 between 01-feb-2021 and 01-apr-2021
		return null;
	}
	
	
	
}
