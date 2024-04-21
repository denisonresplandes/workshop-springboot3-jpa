package com.educandoweb.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.repositories.CategoryRepository;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	/*
	 * database seeding
	 * */
	@Override
	public void run(String... args) throws Exception {
		User user1 = new User("Maria Brown", "maria@gmail.com", 
				"9888888888", "12345");
		User user2 = new User("Alex Green", "alex@gmail.com", 
				"977777777", "12345");
		
		Order o1 = new Order(Instant.parse("2024-04-17T19:53:07Z"), 
				OrderStatus.PAID, user1);
		Order o2 = new Order(Instant.parse("2024-04-18T03:42:10Z"), 
				OrderStatus.WAITING_PAYMENT, user2);
		Order o3 = new Order(Instant.parse("2024-04-19T15:21:22Z"), 
				OrderStatus.WAITING_PAYMENT, user1);
		
		Category c1 = new Category("Electronics");
		Category c2 = new Category("Books");
		Category c3 = new Category("Computers");
		
		userRepository.saveAll(Arrays.asList(user1, user2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		categoryRepository.saveAll(Arrays.asList(c1, c2, c3));
	}
}
