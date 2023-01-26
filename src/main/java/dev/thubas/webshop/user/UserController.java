package dev.thubas.webshop.user;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.thubas.webshop.auth.AuthService;
import dev.thubas.webshop.cart.CartDto;
import dev.thubas.webshop.wishlist.WishlistService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private WishlistService wishlistService;
	
	@Autowired
	private AuthService authService;
	
	private Logger log = LoggerFactory.getLogger(getClass());

	@PostMapping("/signup")
	public ResponseEntity<SignupResponseDto> signup(
			@Valid @RequestBody SignupDto signupDto) {
		userService.signup(signupDto);
		SignupResponseDto response = new SignupResponseDto(
				"User created successfully");
		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}
	
	
	@PostMapping("/signin")
	public ResponseEntity<SigninResponseDto> signin(
			@Valid @RequestBody SigninDto signinDto) {
		SigninResponseDto response = userService.signin(signinDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	@GetMapping("/{userId}/wishlists")
	public ResponseEntity<CartDto> migrateWishlistToCart(
			@RequestParam String token,
			@PathVariable Long userId) {
		log.info("Migrating User : {}'s wishlist to cart", userId);
		authService.authenticate(token);
		User user = authService.getAuthenticatedUser(token);
		CartDto cartDto = wishlistService.migrateAllToCart(user);
		return ResponseEntity.ok(cartDto);
	}

}
