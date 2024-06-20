package com.educandoweb.course.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.repositories.CategoryRepository;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	/**
	 * Saves the given {@link Category}.
	 * 
	 * @param category
	 * @return the saved category
	 * */
	public Category insert(Category category) {
		return repository.save(category);
	}
	
	/**
	 * Retrieves an {@link Category} by its id.
	 * 
	 * @param id
	 * @return the category with the given id
	 * @throws ResourceNotFoundException if the {@link Category} not found
	 * */
	public Category findById(Integer id) {
		Category category = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("category not found"));
		return category;
	}
	
	/**
	 * Returns all categories
	 * 
	 * @return all categories
	 * */
	public List<Category> findAll() {
		return repository.findAll();
	}
}
