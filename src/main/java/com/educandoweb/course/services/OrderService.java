package com.educandoweb.course.services;

import java.util.List;

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
	 * */
	public Order findById(Integer id) {
		Order order = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("order not found"));
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
