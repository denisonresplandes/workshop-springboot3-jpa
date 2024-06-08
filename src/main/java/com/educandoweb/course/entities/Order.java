package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.educandoweb.course.entities.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public final class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Instant moment;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private User client;
	
	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> items;
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;
	
	{
		items = new HashSet<>();
	}
	
	protected Order() { }

	public Order(Instant moment, OrderStatus orderStatus, User client) {
		// TODO bean validation
		validateAttribs(moment, orderStatus, client);
		this.moment = moment;
		this.client = client;
		this.orderStatus = orderStatus;
	}
	
	public Integer getId() {
		return id;
	}
	
	public Instant getMoment() {
		return moment;
	}
	
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	
	public void setOrderStatus(OrderStatus orderStatus) {
		Objects.requireNonNull(orderStatus);
		this.orderStatus = orderStatus;
	}
	
	public User getClient() {
		return client;
	}
	
	public Set<OrderItem> getItems() {
		return Set.copyOf(items);
	}
	
	public Payment getPayment() {
		return payment;
	}
	
	public void setPayment(Payment payment) {
		Objects.requireNonNull(payment);
		this.payment = payment;
	}
	
	private void validateAttribs(Instant moment, OrderStatus orderStatus, 
			User client) {
		Objects.requireNonNull(moment);
		Objects.requireNonNull(client);
		Objects.requireNonNull(orderStatus);
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}
}
