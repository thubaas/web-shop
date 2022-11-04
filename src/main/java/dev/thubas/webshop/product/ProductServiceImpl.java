package dev.thubas.webshop.product;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.thubas.webshop.category.Category;
import dev.thubas.webshop.category.CategoryService;
import dev.thubas.webshop.exception.ProductNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {
	
	private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private CategoryService categoryService;
		
	@Autowired
	private ProductRepository productRepository;

	@Override
	public ProductDto addProduct(ProductDto productDto) {
		Category category = categoryService
				.getCategoryById(productDto.getCategoryId());
		Product product = new Product(
				productDto.getName(), 
				productDto.getImageUrl(), 
				productDto.getPrice(), 
				productDto.getDescription(), 
				category);
		
		Product savedProduct = productRepository.save(product);
		ProductDto savedProductDto = new ProductDto(savedProduct);
		log.info("Added Product : {}", savedProductDto);
		return savedProductDto;		
	}

	@Override
	public List<ProductDto> getProducts() {
		List<Product> products = productRepository.findAll();
		List<ProductDto> productDtos = products
				.stream()
				.map(p -> new ProductDto(p))
				.collect(Collectors.toList());
		return productDtos;
	}

	@Override
	public ProductDto updateProduct(Long productId, ProductDto productDto) {
		String msg = "Product not found";
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException(msg));
		Category category = categoryService
				.getCategoryById(productDto.getCategoryId());
		
		product.setName(productDto.getName());
		product.setCategory(category);
		product.setImageUrl(productDto.getImageUrl());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		Product savedProduct = productRepository.save(product);
		return new ProductDto(savedProduct);		
	}

	@Override
	public boolean existsById(Long productId) {
		return productRepository.existsById(productId);
	}

	@Override
	public ProductDto getProductById(Long id) throws ProductNotFoundException {
		String msg = "Product not found";
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException(msg));
		return new ProductDto(product);
	}
	
	@Override
	public boolean deleteProduct(Long productId) {
		productRepository.deleteById(productId);
		return true;
	}

	@Override
	public Product retrieveProduct(Long id) throws ProductNotFoundException {
		String msg = "Product not found";
		return productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException(msg));
	}

}
