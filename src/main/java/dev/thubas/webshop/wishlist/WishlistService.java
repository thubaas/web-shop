package dev.thubas.webshop.wishlist;

import java.util.List;

import dev.thubas.webshop.product.ProductDto;
import dev.thubas.webshop.user.User;

public interface WishlistService {
	
	List<WishlistItemDto> getWishlistByUser(User user);
	void createWishlist(ProductDto productDto, User user);
	boolean deleteWishlistById(Long id);

}
