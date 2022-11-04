package dev.thubas.webshop.product;

import org.slf4j.LoggerFactory;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.thubas.webshop.auth.AuthService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private AuthService authService;
	
	private final Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@PostMapping("/")
	public ResponseEntity<ProductDto> addProduct(
			@RequestParam String token,
			@Valid @RequestBody ProductDto productDto) {
		
		log.info("Auth Token : {}", token);
		authService.authenticate(token);
		ProductDto product = productService.addProduct(productDto);
		return new ResponseEntity<>(product, HttpStatus.CREATED);
	}
	
	@PutMapping("/{productId}")
	public ResponseEntity<ProductDto> updateProduct(
			@RequestParam String token,
			@PathVariable Long productId,
			@Valid @RequestBody ProductDto productDto) {
		
		log.info("Auth Token : {}", token);
		authService.authenticate(token);
		ProductDto product = productService.updateProduct(productId, productDto);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId) {
		ProductDto product = productService.getProductById(productId);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<ProductDto>> getProducts() {
		List<ProductDto> products = productService.getProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<Boolean> deleteProduct(
			@RequestParam String token,
			@PathVariable Long productId) {
		log.info("Auth Token : {}", token);
		authService.authenticate(token);
		boolean isDeleted = productService.deleteProduct(productId);
		return new ResponseEntity<>(isDeleted, HttpStatus.OK);
	}

}
