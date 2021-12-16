package com.ivanmoreno.exercises.models.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.With;

@Data
@AllArgsConstructor
@ToString(exclude = {"orders"})
@EqualsAndHashCode(exclude = {"orders", "price"})
@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String category;
	
	
	@With Double price;
	
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler", "products"}, allowSetters = true)
	@ManyToMany(mappedBy = "products")
	private List<Order> orders;
	
	public Product() {
		orders = new ArrayList<>();
	}
}
