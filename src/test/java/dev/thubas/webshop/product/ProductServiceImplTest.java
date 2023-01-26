package dev.thubas.webshop.product;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductServiceImplTest {
	
	static final Long PRODUCT_ID = 1L;
	static final String name = "Muffins";
	ProductService productService;
	
	@BeforeEach
	void setUp() {
//		ProductDto productDto = new ProductDto()
		productService = new ProductServiceImpl();
//		productService.addProduct()
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
