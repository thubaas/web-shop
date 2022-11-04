package dev.thubas.webshop.wishlist;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.thubas.webshop.auth.AuthService;
import dev.thubas.webshop.product.ProductDto;
import dev.thubas.webshop.user.User;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/wishlists")
public class WishlistController {
	
	@Autowired
	private WishlistService wishlistService;
	
	@Autowired
	private AuthService authService;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@PostMapping("/")
	public ResponseEntity<Boolean> addWishlist(@RequestParam String token,
			@RequestBody ProductDto productDto) {
		log.info("TOKEN : {}", token);
		
		authService.authenticate(token);
		User user = authService.getAuthenticatedUser(token);
		wishlistService.createWishlist(productDto, user);
		return new ResponseEntity<>(true, HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<WishlistItemDto>> getWishlist(
			@RequestParam String token) {
		log.info("TOKEN : {}", token);
		authService.authenticate(token);
		User user = authService.getAuthenticatedUser(token);
		List<WishlistItemDto> wishlist = wishlistService.getWishlistByUser(user); 
		return ResponseEntity.ok(wishlist);
	}
	
	@DeleteMapping("/{wishlistId}")
	public ResponseEntity<Boolean> deleteWishlist(@RequestParam String token, 
			@PathVariable Long wishlistId){
		log.info("TOKEN : {}", token);
		authService.authenticate(token);
		Boolean result = wishlistService.deleteWishlistById(wishlistId);
		return ResponseEntity.ok(result);
		
	}

}
