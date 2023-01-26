package dev.thubas.webshop.wishlist;

import java.util.List;

import dev.thubas.webshop.cart.CartDto;
import dev.thubas.webshop.product.ProductDto;
import dev.thubas.webshop.user.User;

public interface WishlistService {
	
	List<WishlistDto> getWishlistByUser(User user);
	WishlistDto addToWishlist(ProductDto productDto, User user);
	CartDto migrateAllToCart(User user);
	boolean deleteWishlistById(Long id);

}
