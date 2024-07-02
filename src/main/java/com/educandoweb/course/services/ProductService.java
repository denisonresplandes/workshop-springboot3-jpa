package com.educandoweb.course.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.repositories.ProductRepository;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	/**
	 * Saves the given {@link Product}.
	 * 
	 * @param product
	 * @return the saved product
	 * */
	public Product insert(Product product) {
		return repository.save(product);
	}
	
	/**
	 * Retrieves a {@link Product} by its id.
	 * 
	 * @param id
	 * @return the product with the given id
	 * @throws ResourceNotFoundException if the {@link Product} not found
	 * @throws IllegalArgumentException if the id is null
	 * */
	public Product findById(Integer id) {
		if (Objects.isNull(id)) {
			throw new IllegalArgumentException("the given id must not be null");
		}
		Product product = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("product not found. Id: " + id));
		return product;
	}
	
	/**
	 * Returns all products.
	 * 
	 * @return all products
	 * */
	public List<Product> findAll() {
		return repository.findAll();
	}
}
