package dev.thubas.webshop.wishlist;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.thubas.webshop.cart.Cart;
import dev.thubas.webshop.cart.CartDto;
import dev.thubas.webshop.cart.CartItem;
import dev.thubas.webshop.cart.CartItemDto;
import dev.thubas.webshop.cart.CartItemRepository;
import dev.thubas.webshop.cart.CartRepository;
import dev.thubas.webshop.exception.ProductNotFoundException;
import dev.thubas.webshop.product.Product;
import dev.thubas.webshop.product.ProductDto;
import dev.thubas.webshop.product.ProductRepository;
import dev.thubas.webshop.user.User;

@Service
public class WishlistServiceImpl implements WishlistService {

	@Autowired
	private WishlistRepository wishlistRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;

	@Override
	public List<WishlistDto> getWishlistByUser(User user) {
		List<Wishlist> wishlist = wishlistRepository
				.findAllByUserOrderByCreationDateDesc(user);
		
		List<WishlistDto> wishlistItems = wishlist.stream()
				.map(w -> new WishlistDto(
						w.getId(),
						w.getCreationDate(),
						new ProductDto(w.getProduct())
						)
				)
				.collect(Collectors.toList());

		return wishlistItems;
	}
	
	@Override
	public CartDto migrateAllToCart(User user) {
		List<Wishlist> wishlist = wishlistRepository
				.findAllByUserOrderByCreationDateDesc(user);
		
		Optional<Cart> cartOptional = cartRepository.findByUser(user);
		Cart cart;
		if(!cartOptional.isPresent()) {
			cart = new Cart(null, new Date(), null, user, 0);
			cart = cartRepository.save(cart);
		} else {
			cart = cartOptional.get();
		}
		
		List<CartItem> cartItems = wishlist.stream()
				.map(w -> new CartItem(
						null, 
						1, 
						w.getProduct().getPrice(), 
						null, 
						w.getProduct()
						)
					)
				.collect(Collectors.toList());
		
		
		for(CartItem item : cartItems) {
			Optional<CartItem> itemOptional = cartItemRepository
					.findByProductAndCart(item.getProduct(), item.getCart());
			
			if(itemOptional.isPresent()) {
//				increment quantity and save
				CartItem cartItem = incrementQuantity(itemOptional);
				int index = cart.getItems().indexOf(cartItem);
				cart.getItems().set(index, cartItem);
			} else {
				item.setCart(cart);
				cartItemRepository.save(item);
			}
		}
		
		for(Wishlist w: wishlist) {
			wishlistRepository.deleteById(w.getId());
		}
		
		return maptoCartDto(cart);
	}

	@Override
	public WishlistDto addToWishlist(ProductDto productDto, User user) {
		Product product = productRepository
				.findById(productDto.getId())
				.orElseThrow(
						() -> new ProductNotFoundException("Product not found")
				);
		Optional<Wishlist> saveWishlistOptional = wishlistRepository
				.findByProductAndUser(product, user);
		Wishlist savedWishlist;
		if(!saveWishlistOptional.isPresent()) {
			Wishlist wishlist = new Wishlist(user, product);
			savedWishlist = wishlistRepository.save(wishlist);
		} else {
			savedWishlist = saveWishlistOptional.get();
		}
	
		WishlistDto savedWishlistDto = new WishlistDto(
				savedWishlist.getId(), 
				savedWishlist.getCreationDate(), 
				new ProductDto(savedWishlist.getProduct())
				);
		return savedWishlistDto;
	}

	@Override
	public boolean deleteWishlistById(Long id) {
		wishlistRepository.deleteById(id);
		return true;
	}
	
	private CartDto maptoCartDto(Cart cart) {
		List<CartItemDto> cartItems = cart.getItems().stream()
				.map(item -> new CartItemDto(
						item.getId(), 
						item.getQuantity(), 
						item.getProduct().getId(), 
						item.getCart().getCreationDate(),
						item.getProduct().getPrice(), 
						item.getCart().getId(),
						item.getProduct().getImageUrl(),
						item.getProduct().getName()))
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
	
	private CartItem incrementQuantity(Optional<CartItem> cartItemOptional) {
		CartItem cartItem = cartItemOptional.get();
		cartItem.setQuantity(cartItem.getQuantity() + 1);
		return cartItemRepository.save(cartItem);
	}

}
