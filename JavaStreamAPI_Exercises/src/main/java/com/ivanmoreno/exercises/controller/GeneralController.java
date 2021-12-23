package com.ivanmoreno.exercises.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivanmoreno.exercises.models.entity.Customer;
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
		List<Product> products = service.findAllOrder()
				.stream()
				.filter(order -> order.getCustomer().getTier() == 2)
				.filter(order -> order.getOrderDate().compareTo(LocalDate.of(2021, 2, 1)) >= 0)
				.filter(order -> order.getOrderDate().compareTo(LocalDate.of(2021, 4, 1)) <= 0)
				.flatMap(order -> order.getProducts().stream())
				.distinct()
				.collect(toList());
		
		return products;
	}
	
	@GetMapping("/e5")
	public Product exercise5() {
		//get the cheapest products of "Books" category 
		
		Optional<Product> productOp = service.findAllProduct()
				.stream()
				.filter(product -> product.getCategory().equalsIgnoreCase("Books"))
				.collect(minBy(Comparator.comparingDouble(Product::getPrice)));
		
		return productOp.get();
	}
	
	@GetMapping("/e6")
	public List<Order> exercise6() {
		//Get the 3 most recent placed order 
		
		List<Order> orders = service.findAllOrder()
				.stream()
				.sorted(Comparator.comparing(Order::getOrderDate).reversed())
				.limit(3)
				.collect(toList());
		
		return orders;
	}
	
	@GetMapping("/e7")
	public List<Product> exercise7() {
		//Get a list of orders which were ordered on 15-Mar-2021, 
		//log the order records to the console and then return its products list
		
		List<Product> products = service.findAllOrder()
				.stream()
				.filter(order -> order.getOrderDate().equals(LocalDate.of(2021, 3, 15)))
				.peek(System.out::println)
				.flatMap(order -> order.getProducts().stream())
				.distinct()
				.collect(toList());
		
		return products;
	}
	
	@GetMapping("/e8")
	public Double exercise8() {
		//Calculate total lump sum of all orders placed in Feb 2021
		
		Double totalSump = service.findAllOrder()
				.stream()
				.filter(order -> order.getOrderDate().compareTo(LocalDate.of(2021, 2, 1)) >= 0)
				.filter(order -> order.getOrderDate().compareTo(LocalDate.of(2021, 3, 1)) < 0)
				.flatMap(order -> order.getProducts().stream())
				.mapToDouble(Product::getPrice)
				.sum();
				
		return totalSump;
	}
	
	@GetMapping("/e9")
	public Double exercise9() {
		//Calculate order average payment place on 15-Mar-2021
		
		Double averagePayment = service.findAllOrder()
				.stream()
				.filter(order -> order.getOrderDate().equals(LocalDate.of(2021, 3, 15)))
				.flatMap(order -> order.getProducts().stream())
				.collect(Collectors.averagingDouble(Product::getPrice));
				
		return averagePayment;
	}
	
	@GetMapping("/e10")
	public DoubleSummaryStatistics exercise10() {
		//Obtain a collection of statistic figures (i.e. sum, average, max, min, count) for all products of category "Books"
		
		DoubleSummaryStatistics statistic = service.findAllProduct()
				.stream()
				.filter(product -> product.getCategory().equalsIgnoreCase("books"))
				.collect(Collectors.summarizingDouble(Product::getPrice));
		
		return statistic;
	}
	
	@GetMapping("/e11")
	public Map<Long, Integer> exercise11() {
		//Obtain a data map with order id and order's product count 
		
		Map<Long, Integer> orders = service.findAllOrder()
				.stream()
				.collect(
						toMap(
							Order::getId,
							order -> order.getProducts().size()));
		
		return orders;
	}
	
	@GetMapping("/e12")
	public Map<Customer, List<Order>> exercise12() {
		//Produce a data map with order records grouped by customer 
		
		Map<Customer, List<Order>> ordersByCustomer = service.findAllOrder()
				.stream()
				.collect(groupingBy(Order::getCustomer));
		
		return ordersByCustomer;
		
	}
	
	@GetMapping("/e13")
	public Map<Order, Double> exercise13() {
		//Produce a data map with order record and product total sum 
		
		Map<Order, Double> result = service.findAllOrder()
				.stream()
				.collect(
						toMap(
							Function.identity(), 
							order -> order.getProducts()
							    .stream()
							    .mapToDouble(Product::getPrice).sum())
						);
		
		return result;
	}
	
	@GetMapping("/e14")
	public Map<String, List<String>> exercise14() {
		//Obtain a data map with list of product name by category 
		
		Map<String, List<String>> result = service.findAllProduct()
				.stream()
				.collect(
						groupingBy(
								Product::getCategory, 
								mapping(Product::getName, toList())));
		
		return result;
	}
	
	@GetMapping("/e15")
	public Map<String, Product> exercise15() {
		//Get the most expensive product by category 
		
		Map<String, Product> mostExpensiveProduct = service.findAllProduct()
				.stream()
				.collect(
						groupingBy(
								Product::getCategory, 
								collectingAndThen(
										maxBy(
											Comparator.comparingDouble(Product::getPrice)), 
										    Optional::get)));
		
		return mostExpensiveProduct;
	}
	
}
