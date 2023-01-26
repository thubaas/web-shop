package dev.thubas.webshop.cart;

import dev.thubas.webshop.user.User;

public interface CartService {
	
	CartDto addToCart(CartItemDto cartItemDto, User user);
	CartDto updateCart(CartItemDto cartItemDto, User user);
	boolean deleteCart(Long cartId);
	boolean deleteCartItem(Long cartId,Long cartItemId);
	CartDto getCartById(Long cartId);
	CartDto getCartByUser(User user);
	void deleteByUser(User user);
}
