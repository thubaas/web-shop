package dev.thubas.webshop.wishlist;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Override
	public List<WishlistItemDto> getWishlistByUser(User user) {
		List<Wishlist> wishlist = wishlistRepository
				.findAllByUserOrderByCreationDateDesc(user);
		
		List<WishlistItemDto> wishlistItems = wishlist.stream()
				.map(w -> new WishlistItemDto(
						w.getId(),
						w.getCreationDate(),
						new ProductDto(w.getProduct())
						)
				)
				.collect(Collectors.toList());

		return wishlistItems;
	}

	@Override
	public void createWishlist(ProductDto productDto, User user) {
		Product product = productRepository
				.findById(productDto.getId())
				.orElseThrow(
						() -> new ProductNotFoundException("Product not found")
				);
		Wishlist wishlist = new Wishlist(user, product);
		wishlistRepository.save(wishlist);
	}

	@Override
	public boolean deleteWishlistById(Long id) {
		wishlistRepository.deleteById(id);
		return true;
	}

}
