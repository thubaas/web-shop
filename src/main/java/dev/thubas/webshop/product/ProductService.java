package dev.thubas.webshop.product;

import java.util.List;

import dev.thubas.webshop.exception.ProductNotFoundException;

public interface ProductService {
	
	ProductDto addProduct(ProductDto productDto);
	List<ProductDto> getProducts();
	ProductDto updateProduct(Long productId, ProductDto productDto);
	boolean existsById(Long productId);
	ProductDto getProductById(Long id) throws ProductNotFoundException;
	Product retrieveProduct(Long id) throws ProductNotFoundException;
	boolean deleteProduct(Long id);
}
