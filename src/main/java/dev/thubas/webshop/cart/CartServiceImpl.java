package dev.thubas.webshop.cart;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.thubas.webshop.exception.CartNotFoundException;
import dev.thubas.webshop.user.User;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepository cartRepository;
		
	@Autowired
	private CartItemRepository cartItemRepository;

	@Override
	public Cart addCart(Cart cart, User user) {
				
		double totalCost = 0;
		for(CartItem item : cart.getItems()) {
			totalCost += item.getProduct().getPrice() * item.getQuantity();
		}
		cart.setCreationDate(new Date());
		cart.setUser(user);
		cart.setTotalCost(totalCost);
		Cart savedCart = cartRepository.save(cart);
		
		for(CartItem item : cart.getItems()) {
			item.setCart(savedCart);
			cartItemRepository.save(item);
		}
		
		return savedCart;
	}

	@Override
	public boolean deleteCart(Long cartId) {
		cartItemRepository.deleteById(cartId);
		return true;
	}

	@Override
	public Cart getCartById(Long cartId) {
		return cartRepository
				.findById(cartId)
				.orElseThrow(() -> new CartNotFoundException("Cart not found"));
	}

	@Override
	public Cart getCartByUser(User user) {
		return cartRepository
				.findByUser(user)
				.orElseThrow(() -> new CartNotFoundException("Cart not found"));
	}
	

}
