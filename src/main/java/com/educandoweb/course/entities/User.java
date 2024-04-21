package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private String phone;
	
	@JsonIgnore
	private String password;
	
	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private List<Order> orders;
	
	{
		orders = new ArrayList<>();
	}
	
	protected User() { }
	
	public User(String name, String email, String phone, String password) {
		validateAttribs(name, email, phone, password);
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		Objects.requireNonNull(email);
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		Objects.requireNonNull(phone);
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		Objects.requireNonNull(password);
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public List<Order> getOrders() {
		return Collections.unmodifiableList(orders);
	}
	
	private void validateAttribs(String name, String email, 
			String phone, String password) {
		Objects.requireNonNull(name);
		Objects.requireNonNull(email);
		Objects.requireNonNull(phone);
		Objects.requireNonNull(password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
}
