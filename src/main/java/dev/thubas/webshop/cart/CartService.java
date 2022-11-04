package dev.thubas.webshop.cart;

import dev.thubas.webshop.user.User;

public interface CartService {
	
	Cart addCart(Cart cart, User user);
	boolean deleteCart(Long cartId);
	Cart getCartById(Long cartId);
	Cart getCartByUser(User user);
}
