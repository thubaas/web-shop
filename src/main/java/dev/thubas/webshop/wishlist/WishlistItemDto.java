package dev.thubas.webshop.wishlist;

import java.util.Date;

import dev.thubas.webshop.product.ProductDto;

public class WishlistItemDto {

	private Long id;

	private Date creationDate;

	private ProductDto productDto;

	public WishlistItemDto() {
		super();
	}

	public WishlistItemDto(Long id, Date creationDate, ProductDto productDto) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.productDto = productDto;
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

	public ProductDto getProductDto() {
		return productDto;
	}

	public void setProductDto(ProductDto productDto) {
		this.productDto = productDto;
	}

}
