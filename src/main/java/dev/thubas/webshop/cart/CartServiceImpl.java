package dev.thubas.webshop.cart;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.thubas.webshop.exception.CartNotFoundException;
import dev.thubas.webshop.exception.ProductNotFoundException;
import dev.thubas.webshop.product.Product;
import dev.thubas.webshop.product.ProductRepository;
import dev.thubas.webshop.user.User;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public CartDto addToCart(CartItemDto cartItemDto, User user) {

		Optional<Cart> cartOptional = cartRepository.findByUser(user);
		Cart cart;
		if (!cartOptional.isPresent()) {
			cart = new Cart(null, new Date(), null, user, 0);
			cart = cartRepository.save(cart);
		} else {
			cart = cartOptional.get();
		}

		Product product = productRepository.findById(cartItemDto.getProductId())
				.orElseThrow(() -> new ProductNotFoundException(
						"Product " + cartItemDto.getProductId() + "not found"));

		Optional<CartItem> cartItemOptional = cartItemRepository.findByProduct(product);

		if (cartItemOptional.isPresent()) {
			CartItem cartItem = incrementQuantity(cartItemOptional);
			int index = cart.getItems().indexOf(cartItem);
			cart.getItems().set(index, cartItem);
		} else {
			createCartItem(cartItemDto, cart, product);
		}

		CartDto cartDto = maptoCartDto(cart);
		return cartDto;
	}

	private CartItem createCartItem(CartItemDto cartItemDto, Cart cart, Product product) {
		CartItem cartItem = new CartItem(
				null, 
				cartItemDto.getQuantity(), 
				cartItemDto.getPrice(), 
				cart, product
				);
		return cartItemRepository.save(cartItem);
	}

	private CartItem incrementQuantity(Optional<CartItem> cartItemOptional) {
		CartItem cartItem = cartItemOptional.get();
		cartItem.setQuantity(cartItem.getQuantity() + 1);
		return cartItemRepository.save(cartItem);
	}

	@Override
	public boolean deleteCart(Long cartId) {
		cartRepository.deleteById(cartId);
		return true;
	}

	@Override
	public boolean deleteCartItem(Long cartId, Long cartItemId) {
		cartItemRepository.deleteById(cartItemId);
		return true;
	}

	@Override
	public CartDto getCartById(Long cartId) {
		Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException("Cart not found"));
		return maptoCartDto(cart);
	}

	@Override
	public CartDto getCartByUser(User user) {
		Optional<Cart> cartOtOptional = cartRepository.findByUser(user);
		if (cartOtOptional.isPresent())
			return maptoCartDto(cartOtOptional.get());
		Cart cart = new Cart(null, new Date(), null, user, 0);
		Cart savedCart = cartRepository.save(cart);
		return maptoCartDto(savedCart);
	}

	@Override
	public CartDto updateCart(CartItemDto cartItemDto, User user) {

		CartItem cartItem = cartItemRepository.findById(cartItemDto.getId()).get();
		cartItem.setQuantity(cartItemDto.getQuantity());
		CartItem savedItem = cartItemRepository.save(cartItem);

		Cart cart = cartRepository.findById(savedItem.getCart().getId()).get();
		return maptoCartDto(cart);
	}

	private CartDto maptoCartDto(Cart cart) {
		List<CartItemDto> cartItems = null;
		if (cart.getItems() != null)
			cartItems = cart.getItems().stream()
					.map(item -> new CartItemDto(
							item.getId(), 
							item.getQuantity(), 
							item.getProduct().getId(),
							item.getCart().getCreationDate(), 
							item.getProduct().getPrice(), 
							item.getCart().getId(),
							item.getProduct().getImageUrl(), 
							item.getProduct().getName()
							)
						)
					.collect(Collectors.toList());

		CartDto cartDto = new CartDto(
				cart.getId(), 
				cart.getTotalCost(), 
				cart.getCreationDate(), 
				cartItems,
				cart.getUser().getId()
				);
		return cartDto;
	}

	@Override
	public void deleteByUser(User user) {
		cartRepository.deleteByUser(user);
		
	}

}
