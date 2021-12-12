package com.ivanmoreno.exercises.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString(exclude = {"products"})
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String status;
	
	@Column(name = "order_date")
	private Date orderDate;
	
	@Column(name = "delivery_date")
	private Date deliveryDate;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler", "orders"}, allowSetters = true)
	@ManyToMany
	@JoinTable(
			name = "order_product_relationship",
			joinColumns = {@JoinColumn(name = "order_id")},
			inverseJoinColumns = {@JoinColumn(name = "product_id")}
			)
	private List<Product> products;
	
	public Order() {
		products = new ArrayList<>();
	}
}
