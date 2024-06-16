package com.educandoweb.course.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exception.NotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	/**
	 * Saves the given {@link User}.
	 * 
	 * @param user
	 * @return the saved user
	 * */
	public User insert(User user) {
		return repository.save(user);
	}
	
	/**
	 * Deletes the {@link User} with the given id.
	 * 
	 * @param id
	 * @throws NotFoundException if the {@link User} not found
	 * */
	public void delete(Integer id) {
		repository.findById(id).ifPresentOrElse(user -> {
			repository.delete(user);
		}, () -> { 
			throw new NotFoundException("user not found");
		});
	}
	
	/**
	 * Retrieves an {@link User} by its id.
	 * 
	 * @param id
	 * @return the user with the given id
	 * @throws NotFoundException if the {@link User} not found
	 * */
	public User findById(Integer id) {
		User user = repository.findById(id)
			.orElseThrow(() -> new NotFoundException("user not found"));
		return user;
	}
	
	/**
	 * Returns all users.
	 * 
	 * @return all users
	 * */
	public List<User> findAll() {
		return repository.findAll();
	}
}
