package com.educandoweb.course.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.educandoweb.course.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("from Product p join fetch p.categories order by p.id")
	List<Product> findAll();
	
	@Query("from Product p join fetch p.categories where p.id = ?1")
	Optional<Product> findById(Integer id);
}
