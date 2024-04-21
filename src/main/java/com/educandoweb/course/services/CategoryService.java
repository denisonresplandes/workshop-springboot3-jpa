package com.educandoweb.course.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	public Category save(Category category) {
		Objects.requireNonNull(category, "category can't be null");
		return repository.save(category);
	}
	
	public Category findById(Integer id) {
		Objects.requireNonNull(id, "category id can't be null");
		Category category = repository.findById(id)
			.orElseThrow(() -> new RuntimeException("category not found"));
		return category;
	}
	
	public List<Category> findAll() {
		return repository.findAll();
	}
}
