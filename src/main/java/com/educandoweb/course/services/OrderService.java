package com.educandoweb.course.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	public Order save(Order order) {
		Objects.requireNonNull(order, "order can't be null");
		return repository.save(order);
	}
	
	public Order findById(Integer id) {
		Objects.requireNonNull(id, "id can't be null");
		Order order = repository.findById(id)
			.orElseThrow(() -> new RuntimeException("order not found"));
		return order;
	}
	
	public List<Order> findAll() {
		return repository.findAll();
	}
}
