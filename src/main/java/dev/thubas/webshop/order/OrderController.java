package dev.thubas.webshop.order;

import java.util.List;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.thubas.webshop.auth.AuthService;
import dev.thubas.webshop.cart.CartItemDto;
import dev.thubas.webshop.shared.ApiResponse;
import dev.thubas.webshop.user.User;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private AuthService authService;
	
	
	@PostMapping("/create-checkout-session")
	public ResponseEntity<ApiResponse> checkout(
			@RequestBody List<CartItemDto> checkoutItems, 
			@RequestParam String token) 
					throws StripeException {
		authService.authenticate(token);
		User authenticatedUser = authService.getAuthenticatedUser(token);
		
//		create the stripe session 
		Session session = orderService
				.createSession(checkoutItems, authenticatedUser);
		
		ApiResponse response = new ApiResponse(true, session.getId());
		return ResponseEntity.ok(response);
		
	}
	
	@PostMapping("/")
	public ResponseEntity<Boolean> placeOrder(
			@RequestParam String token, 
			@RequestParam String sessionId
			) {
		
		User user = authService.getAuthenticatedUser(token);
		orderService.placeOrder(user, sessionId);
		return new ResponseEntity<>(true, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/")
	public ResponseEntity<List<Order>> getAllOrders(@RequestParam String token) {
		User user = authService.getAuthenticatedUser(token);
		List<Order> orders = orderService.getOrdersByUser(user);
		return ResponseEntity.ok(orders);
	}
	

}
