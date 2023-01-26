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

	private double price;

	private Long cartId;
	
	private String imageUrl;
	
	private String productName;

	public CartItemDto() {
		super();
	}

	public CartItemDto(Long id, @NotNull Integer quantity, 
			@NotNull Long productId, Date creationDate, @NotNull double price,
			@NotNull Long cartId, String imageUrl, String productName) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.productId = productId;
		this.creationDate = creationDate;
		this.price = price;
		this.cartId = cartId;
		this.imageUrl = imageUrl;
		this.productName = productName;
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
	
	

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "CartItemDto [id=" + id + ", quantity=" + quantity + ","
				+ " productId=" + productId + ", creationDate="
				+ creationDate + ", price=" + price + ", cartId=" + cartId 
				+ ", imageUrl=" + imageUrl + ", productName="
				+ productName + "]";
	}


	
}
