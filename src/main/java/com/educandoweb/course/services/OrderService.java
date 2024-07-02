package com.educandoweb.course.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	/**
	 * Saves the given {@link Order}.
	 * 
	 * @param order
	 * @return the saved order
	 * */
	public Order insert(Order order) {
		return repository.save(order);
	}
	
	/**
	 * Retrieves an {@link Order} by its id.
	 * 
	 * @param id
	 * @return the order with the given id 
	 * @throws ResourceNotFoundException if the {@link Order} not found
	 * @throws IllegalArgumentException if the id is null
	 * */
	public Order findById(Integer id) {
		if (Objects.isNull(id)) {
			throw new IllegalArgumentException("the given id must not be null");
		}
		Order order = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("order not found. Id: " + id));
		return order;
	}
	
	/**
	 * Returns all orders.
	 * 
	 * @return all orders
	 * */
	public List<Order> findAll() {
		return repository.findAll();
	}
}
