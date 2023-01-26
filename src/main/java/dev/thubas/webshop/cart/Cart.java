package dev.thubas.webshop.cart;

import java.util.ArrayList;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import dev.thubas.webshop.user.User;


@Entity
@Table(name = "carts")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date creationDate;
	
	@OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
	private List<CartItem> items;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@Transient
	private double totalCost;

	public Cart() {
		super();
	}

	public Cart(Long id, @NotNull Date creationDate, List<CartItem> items, 
			User user, double totalCost) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.items = items;
		this.user = user;
		this.totalCost = totalCost;
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

	public List<CartItem> getItems() {
		if(items == null)
			return new ArrayList<>();
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getTotalCost() {
		double total = calculateTotalCost();
		return total;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	
	private double calculateTotalCost() {
		double total = 0;
		if(items != null)
			for(CartItem item : items)
				total += item.getQuantity() * item.getProduct().getPrice();
		return total;
	}

}
