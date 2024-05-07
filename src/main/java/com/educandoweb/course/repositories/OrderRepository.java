package com.educandoweb.course.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.educandoweb.course.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Query("from Order o join fetch o.client join fetch o.items oi")
	List<Order> findAll();
}
