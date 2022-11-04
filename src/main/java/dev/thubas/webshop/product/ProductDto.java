package dev.thubas.webshop.product;

import javax.validation.constraints.NotNull;

public class ProductDto {

	private Long id;
	@NotNull
	private String name;
	@NotNull
	private String imageUrl;
	@NotNull
	private double price;
	@NotNull
	private String description;
	@NotNull
	private Long categoryId;

	public ProductDto(Long id, @NotNull String name, @NotNull String imageUrl, @NotNull double price,
			@NotNull String description, @NotNull Long categoryId) {
		super();
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
		this.price = price;
		this.description = description;
		this.categoryId = categoryId;
	}

	public ProductDto(Product product) {
		super();
		this.id = product.getId();
		this.name = product.getName();
		this.imageUrl = product.getImageUrl();
		this.price = product.getPrice();
		this.description = product.getDescription();
		this.categoryId = product.getCategory().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", name=" + name + ", imageUrl=" + imageUrl + ", price=" + price
				+ ", description=" + description + ", categoryId=" + categoryId + "]";
	}

}
