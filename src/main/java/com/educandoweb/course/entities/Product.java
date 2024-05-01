package com.educandoweb.course.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

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
	
	@ManyToMany
	@JoinTable(name = "product_category",
		joinColumns = @JoinColumn(name = "product_id"),
		inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories;
	
	{
		categories = new HashSet<>();
	}
	
	protected Product() { }

	public Product(String name, String description, BigDecimal price, 
			String imgUrl) {
		// TODO bean validation
		validateAttribs(name, description, price, imgUrl);
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
		this.imgUrl = Objects.nonNull(imgUrl) ? imgUrl : "";
	}
	
	public Set<Category> getCategories() {
		return Collections.unmodifiableSet(categories);
	}
	
	public void addCategory(Category category) {
		Objects.requireNonNull(category, "category can't be null");
		categories.add(category);
	}
	
	public void removeCategory(Category category) {
		Objects.requireNonNull(category, "category can't be null");
		categories.remove(category);
	}
	
	public static Product createDeepCopy(Product product) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(mapper.writeValueAsString(product), 
					Product.class);
		}
		catch (JsonProcessingException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	private void validateAttribs(String name, String description, BigDecimal price, 
			String imgUrl) {
		Objects.requireNonNull(name);
		Objects.requireNonNull(description);
		Objects.requireNonNull(price);
		Objects.requireNonNull(imgUrl);
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
