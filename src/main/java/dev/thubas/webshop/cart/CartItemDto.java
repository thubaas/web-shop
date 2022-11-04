package dev.thubas.webshop.cart;

import java.util.Date;
import javax.validation.constraints.NotNull;

public class CartItemDto {
	
	private Long id;
	
	@NotNull
	private Integer quantity;
	
	@NotNull
	private Long productId;

	private Date creationDate;

	@NotNull
	private double price;

	@NotNull
	private Long cartId;

	public CartItemDto() {
		super();
	}

	public CartItemDto(Long id, @NotNull Integer quantity, 
			@NotNull Long productId, Date creationDate, @NotNull double price,
			@NotNull Long cartId) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.productId = productId;
		this.creationDate = creationDate;
		this.price = price;
		this.cartId = cartId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	@Override
	public String toString() {
		return "CartItemDto [id=" + id + ", quantity=" + quantity 
				+ ", productId=" + productId + ", creationDate="
				+ creationDate + ", price=" + price + ", cartId=" 
				+ cartId + "]";
	}
	
}
