package dev.thubas.webshop.cart;

import java.util.Date;
import java.util.List;

public class CartDto {
	
	private Long id;
	private double totalCost;
	private Date creationDate;
	private List<CartItemDto> items;
	private Long userId;
	
	public CartDto() {
		super();
	}
	
	public CartDto(Long id, double totalCost, Date creationDate, 
			List<CartItemDto> cartItems, Long userId) {
		super();
		this.id = id;
		this.totalCost = totalCost;
		this.creationDate = creationDate;
		this.items = cartItems;
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public List<CartItemDto> getItems() {
		return items;
	}

	public void setItems(List<CartItemDto> items) {
		this.items = items;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
