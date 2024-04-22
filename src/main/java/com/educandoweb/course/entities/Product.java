package com.educandoweb.course.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "product")
public final class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	
	@Column(precision = 10, scale = 2)
	private BigDecimal price;
	private String imgUrl;
	
	@Transient
	private Set<Category> categories;
	
	{
		categories = new HashSet<>();
	}
	
	protected Product() { }

	public Product(String name, String description, BigDecimal price, 
			String imgUrl) {
		// TODO bean validation
		validateAttribs(name, description, price);
		this.name = name;
		this.description = description;
		setPrice(price);
		this.imgUrl = imgUrl;
	}
	
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		if (price.intValue() <= BigDecimal.ZERO.intValue()) {
			throw new IllegalArgumentException("price can't be equal to or less than 0");
		}
		this.price = price;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public Set<Category> getCategories() {
		return Collections.unmodifiableSet(categories);
	}
	
	private void validateAttribs(String name, String description, BigDecimal price) {
		Objects.requireNonNull(name);
		Objects.requireNonNull(description);
		Objects.requireNonNull(price);
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
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}
}