package dev.thubas.webshop.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import dev.thubas.webshop.cart.Cart;
import dev.thubas.webshop.cart.CartDto;
import dev.thubas.webshop.cart.CartItemDto;
import dev.thubas.webshop.cart.CartService;
import dev.thubas.webshop.product.ProductRepository;
import dev.thubas.webshop.user.User;

@Service
@Transactional
public class OrderService {

	@Value("${BASE_URL}")
	private String baseUrl;

	@Value("${STRIPE_SECRET_KEY}")
	private String apiKey;

	@Autowired
	private CartService cartService;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	private Logger log = LoggerFactory.getLogger(getClass());

		
	public Session createSession(List<CartItemDto> checkoutItems, User user) 
			throws StripeException {
		
//		set up success and failure urls for stripe payment 
		String successUrl = baseUrl + "payment/success";
		String failureUrl = baseUrl + "payment/failed";
		
//		set the private key 
		Stripe.apiKey = apiKey;
		
//		create SessionCreateParams.LineItem for each product
		List<SessionCreateParams.LineItem> sessionItemsList = new ArrayList<>();
		for(CartItemDto item : checkoutItems)
			sessionItemsList.add(createSessionLineItem(item));
		
//		build the session param 
		SessionCreateParams params = SessionCreateParams.builder()
				.addPaymentMethodType(
						SessionCreateParams.PaymentMethodType.CARD
						)
				.setMode(SessionCreateParams.Mode.PAYMENT)
				.setCancelUrl(failureUrl)
				.addAllLineItem(sessionItemsList)
				.setSuccessUrl(successUrl)
				.build();
		
		Session session = Session.create(params);
		log.info("Session : {}", session);
		
		deleteCart(user, checkoutItems);
		
		return session;
		
	}
	
	public void placeOrder (User user, String sessionId) {
		CartDto cartDto = cartService.getCartByUser(user);
		List<CartItemDto> cartItemDtos = cartDto.getItems();
		
//		create order list 
		Order newOrder = new Order();
		newOrder.setCreationDate(new Date());
		newOrder.setSessionId(sessionId);
		newOrder.setUser(user);
		newOrder.setTotalPrice(cartDto.getTotalCost());
		orderRepository.save(newOrder);
		
		for(CartItemDto cartItemDto: cartItemDtos) {
//			create and save each order item 
			OrderItem orderItem = new OrderItem();
			orderItem.setCreationDate(new Date());
			orderItem.setPrice(cartItemDto.getPrice());
			orderItem.setProduct(
					productRepository.findById(cartItemDto.getProductId()).get()
					);
			orderItem.setQuantity(cartItemDto.getQuantity());
			orderItem.setOrder(newOrder);
			orderItemRepository.save(orderItem);
		}
	}
	
	public List<Order> getOrdersByUser(User user) {
		return orderRepository.findAllByUserOrderByCreationDateDesc(user);
	}
	
	private SessionCreateParams.LineItem.PriceData createPriceData(
			CartItemDto cartItemDto) {
		return SessionCreateParams.LineItem.PriceData.builder()
				.setCurrency("usd")
				.setUnitAmount(((long) cartItemDto.getPrice()) * 100)
				.setProductData(
					SessionCreateParams.LineItem.PriceData.ProductData.builder()
						.setName(cartItemDto.getProductName())
						.build())
				.build();
	}
	
//	build each product in the stripe checkout page
	private SessionCreateParams.LineItem createSessionLineItem(
			CartItemDto cartItemDto) {
		return SessionCreateParams.LineItem.builder()
				.setPriceData(createPriceData(cartItemDto))
				.setQuantity(
						Long.parseLong(
								String.valueOf(cartItemDto.getQuantity())
								)
						)
				.build();
		
	}
	
	private void deleteCart(User user, List<CartItemDto> cartitems) {
		for(CartItemDto item : cartitems)
			cartService.deleteCartItem(item.getCartId(), item.getId());
		cartService.deleteByUser(user);
	}

}
