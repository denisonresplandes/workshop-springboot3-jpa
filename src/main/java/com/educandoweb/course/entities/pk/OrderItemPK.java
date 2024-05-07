package com.educandoweb.course.entities.pk;

import java.util.Objects;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.Product;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public final class OrderItemPK {
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	protected OrderItemPK() { }
	
	public OrderItemPK(Order order, Product product) {
		Objects.requireNonNull(order);
		Objects.requireNonNull(product);
		this.order = order;
		this.product = product;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public Product getProduct() {
		return product;
	}

	@Override
	public int hashCode() {
		return Objects.hash(order, product);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemPK other = (OrderItemPK) obj;
		return Objects.equals(order, other.order) && Objects.equals(product, other.product);
	}
}
