package com.ivanmoreno.exercises.controller;

import java.util.List;
import static java.util.stream.Collectors.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
