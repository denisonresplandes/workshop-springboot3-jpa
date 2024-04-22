package com.educandoweb.course.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	public Product save(Product product) {
		Objects.requireNonNull(product, "product can't be null");
		return repository.save(product);
	}
	
	public Product findById(Integer id) {
		Objects.requireNonNull(id, "product id can't be null");
		Product product = repository.findById(id)
			.orElseThrow(() -> new RuntimeException("product not found"));
		return product;
	}
	
	public List<Product> findAll() {
		return repository.findAll();
	}
}
