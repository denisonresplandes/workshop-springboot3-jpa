package com.educandoweb.course.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public User save(User user) {
		Objects.requireNonNull(user, "user can't be null");
		return repository.save(user);
	}
	
	public User findById(Integer id) {
		Objects.requireNonNull(id, "user id can't be null");
		User user = repository.findById(id)
			.orElseThrow(() -> new RuntimeException("user not found"));
		return user;
	}
	
	public List<User> findAll() {
		return repository.findAll();
	}
}
