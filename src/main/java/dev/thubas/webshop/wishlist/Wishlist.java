package dev.thubas.webshop.wishlist;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import dev.thubas.webshop.product.Product;
import dev.thubas.webshop.user.User;

@Entity
@Table(name = "wishlists")
public class Wishlist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;
	
	private Date creationDate;
	
	@ManyToOne()
	@JoinColumn(name = "product_id")
	private Product product;

	public Wishlist() {
		super();
	}

	public Wishlist(User user, Product product) {
		super();
		this.user = user;
		this.product = product;
		this.creationDate = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
