package com.educandoweb.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		User user1 = new User("Maria Brown", "maria@gmail.com", 
				"9888888888", "12345");
		User user2 = new User("Alex Green", "alex@gmail.com", 
				"977777777", "12345");
		
		repository.saveAll(Arrays.asList(user1, user2));
	}
}
