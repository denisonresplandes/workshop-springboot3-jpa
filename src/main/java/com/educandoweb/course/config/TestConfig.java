package com.educandoweb.course.config;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.OrderItem;
import com.educandoweb.course.entities.Payment;
import com.educandoweb.course.entities.Product;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.repositories.CategoryRepository;
import com.educandoweb.course.repositories.OrderItemRepository;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.ProductRepository;
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
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	/*
	 * database seeding
	 * */
	@Override
	public void run(String... args) throws Exception {
		User user1 = new User("Maria Brown", "maria@gmail.com", "9888888888", "12345");
		User user2 = new User("Alex Green", "alex@gmail.com", "977777777", "12345");
		
		Order o1 = new Order(Instant.parse("2024-04-17T19:53:07Z"), OrderStatus.PAID, user1);
		Order o2 = new Order(Instant.parse("2024-04-18T03:42:10Z"), OrderStatus.WAITING_PAYMENT, user2);
		Order o3 = new Order(Instant.parse("2024-04-19T15:21:22Z"), OrderStatus.WAITING_PAYMENT, user1);
		
		Category c1 = new Category("Electronics");
		Category c2 = new Category("Books");
		Category c3 = new Category("Computers");
		
		Product p1 = new Product("The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 
				BigDecimal.valueOf(90.5), "");
		Product p2 = new Product("Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 
				BigDecimal.valueOf(2190.0), "");
		Product p3 = new Product("Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 
				BigDecimal.valueOf(1250.0), "");
		Product p4 = new Product("PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 
				BigDecimal.valueOf(1200.0), "");
		Product p5 = new Product("Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 
				BigDecimal.valueOf(100.99), "");
		
		categoryRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		p1.addCategory(c2);
		p2.addCategory(c1);
		p2.addCategory(c3);
		p3.addCategory(c3);
		p4.addCategory(c3);
		p5.addCategory(c2);
		
		userRepository.saveAll(Arrays.asList(user1, user2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));		
		
		Payment pay1 = new Payment(Instant.parse("2024-04-17T21:53:07Z"), o1);
		o1.setPayment(pay1);
		orderRepository.save(o1);
	}
}
