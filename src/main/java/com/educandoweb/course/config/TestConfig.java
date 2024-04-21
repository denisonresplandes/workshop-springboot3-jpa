package com.educandoweb.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	/*
	 * database seeding
	 * */
	@Override
	public void run(String... args) throws Exception {
		User user1 = new User("Maria Brown", "maria@gmail.com", 
				"9888888888", "12345");
		User user2 = new User("Alex Green", "alex@gmail.com", 
				"977777777", "12345");
		
		Order o1 = new Order(Instant.parse("2024-04-17T19:53:07Z"), user1);
		Order o2 = new Order(Instant.parse("2024-04-18T03:42:10Z"), user2);
		Order o3 = new Order(Instant.parse("2024-04-19T15:21:22Z"), user1);
		
		userRepository.saveAll(Arrays.asList(user1, user2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
	}
}
