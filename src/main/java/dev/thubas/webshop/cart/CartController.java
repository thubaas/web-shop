package dev.thubas.webshop.cart;

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
import dev.thubas.webshop.user.User;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/carts")
public class CartController {
	
	@Autowired
	private CartService cartService;

	@Autowired
	private AuthService authService;
	
	@PostMapping("/")
	public ResponseEntity<Cart> addCart(
			@RequestParam String token, 
			@RequestBody Cart cart) {
		authService.authenticate(token);
		User user = authService.getAuthenticatedUser(token);
		Cart savedCart = cartService.addCart(cart, user);
		return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<Cart> getCart(@RequestParam String token) {
		authService.authenticate(token);
		User user = authService.getAuthenticatedUser(token);
		Cart cart = cartService.getCartByUser(user);
		return new ResponseEntity<>(cart, HttpStatus.OK);
	}
	
	@DeleteMapping("/{cartId}")
	public ResponseEntity<Boolean> deleteCart(
			@RequestParam String token,
			@PathVariable Long cartId) {
		authService.authenticate(token);
		boolean isRemoved = cartService.deleteCart(cartId);
		return new ResponseEntity<>(isRemoved, HttpStatus.OK);
	}
}
