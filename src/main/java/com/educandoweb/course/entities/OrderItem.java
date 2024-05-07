package com.educandoweb.course.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import com.educandoweb.course.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_item")
public final class OrderItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderItemPK id;
	
	private Integer quantity;
	
	@Column(precision = 10, scale = 2)
	private BigDecimal price;
	
	protected OrderItem() { }
	
	public OrderItem(Order order, Product product, Integer quantity, BigDecimal price) {
		validateAttribs(quantity, price);
		this.price = price;
		this.quantity = quantity;
		id = new OrderItemPK(order, product);
	}
	
	@JsonIgnore
	public Order getOrder() {
		return id.getOrder();
	}
	
	public Product getProduct() {
		return id.getProduct();
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal subTotal() {
		return BigDecimal.valueOf(price.doubleValue() * quantity);
	}

	private void validateAttribs(Integer quantity, BigDecimal price) {
		Objects.requireNonNull(price);
		Objects.requireNonNull(quantity);
		if (quantity < 1) {
			throw new IllegalArgumentException("quantity can't be less than 1.");
		}
		if (price.doubleValue() < BigDecimal.ZERO.doubleValue()) {
			throw new IllegalArgumentException("price can't be less than 0.");
		}
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
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}
}
