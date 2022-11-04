package dev.thubas.webshop.order;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dev.thubas.webshop.user.User;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@NotNull
	private Date creationDate;
	
	private double totalPrice;
	
	private String sessionId;
	
	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
	private List<OrderItem> orderItems;
	
	@ManyToOne()
	@JsonIgnore
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	public Order() {
		super();
	}

	public Order(Long id, @NotNull Date creationDate, double totalPrice, 
			String sessionId, List<OrderItem> orderItems, User user) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.totalPrice = totalPrice;
		this.sessionId = sessionId;
		this.orderItems = orderItems;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
