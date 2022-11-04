package dev.thubas.webshop.category;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private AuthService authService;

	final Logger log = LoggerFactory.getLogger(CategoryController.class);

	@PostMapping("/")
	public ResponseEntity<Category> createCategory(@RequestParam("token")
	String token, @Valid @RequestBody Category category) {
		authenticate(token);
		Category savedCategory = categoryService.createCategory(category);
		return ResponseEntity.ok(savedCategory);
	}

	@GetMapping("/")
	public ResponseEntity<List<Category>> getCategories() {
		List<Category> categories = categoryService.getCategories();
		return ResponseEntity.ok(categories);
	}

	@PutMapping("/{categoryId}")
	public ResponseEntity<Category> updateCategory(
			@RequestParam("token") String token,
			@PathVariable Long categoryId, 
			@Valid @RequestBody Category category) {
		authenticate(token);
		Category updatedCategory = categoryService
				.updateCategory(categoryId, category);
		return ResponseEntity.ok(updatedCategory);

	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<Boolean> deleteCategory(
			@RequestParam("token") String token,
			@PathVariable Long categoryId) {
		authenticate(token);
		boolean isDeleted = categoryService.deleteCategory(categoryId);
		return ResponseEntity.ok(isDeleted);
	}
	
	private void authenticate(String token) {
		log.info("AUTH TOKEN : {}", token);
		authService.authenticate(token);
	}

}
