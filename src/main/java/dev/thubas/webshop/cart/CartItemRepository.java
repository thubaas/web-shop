package dev.thubas.webshop.cart;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.thubas.webshop.product.Product;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	
	Optional<CartItem> findByProduct(Product product);

	Optional<CartItem> findByProductAndCart(Product product, Cart cart);

}
