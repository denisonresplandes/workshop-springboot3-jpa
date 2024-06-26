package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@DynamicUpdate
@Table(name = "tb_user")
public final class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "{notBlank.message}")
	@Size(min = 2, message = "{size.message}")
	private String name;
	
	@Email(message = "{email.message}")
	@NotBlank(message = "{notBlank.message}")
	private String email;
	
	@Column(length = 100)
	private String phone;
	
	@NotBlank(message = "notBlank.message")
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
	
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		Objects.requireNonNull(name);
		if (name.isEmpty() || name.isBlank()) {
			throw new IllegalArgumentException("name can't be empty or blank");
		}
		this.name = name;
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
	
	public void setPassword(String password) {
		Objects.requireNonNull(password);
		if (password.isEmpty()) {
			throw new IllegalArgumentException("password can't be empty");
		}
		this.password = password;
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
