package dev.thubas.webshop.cart;

import javax.validation.constraints.NotNull;

public class AddToCartDto {
	
	private Long id;
	
	@NotNull
	private Long productId;
	@NotNull
	private Integer quantity;
	
	public AddToCartDto() {
		super();
	}
	
	public AddToCartDto(Long id, @NotNull Long productId, 
			@NotNull Integer quantity) {
		super();
		this.id = id;
		this.productId = productId;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "AddToCartDto [id=" + id + ", productId=" + productId +
				",quantity=" + quantity + "]";
	}
	
}
