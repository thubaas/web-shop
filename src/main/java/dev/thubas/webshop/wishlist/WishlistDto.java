package dev.thubas.webshop.wishlist;

import java.util.Date;

import dev.thubas.webshop.product.ProductDto;

public class WishlistDto {

	private Long id;

	private Date creationDate;

	private ProductDto product;

	public WishlistDto() {
		super();
	}

	public WishlistDto(Long id, Date creationDate, ProductDto productDto) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.product = productDto;
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

	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(ProductDto product) {
		this.product = product;
	}

}
