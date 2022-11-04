package dev.thubas.webshop.category;

import java.util.List;

public interface CategoryService {
	
	boolean existsByName(String name);
	boolean existsById(Long id);
	Category createCategory(Category category);
	Category updateCategory(Long categoryId, Category category);
	Category getCategoryByName(String name);
	List<Category> getCategories();
	Category getCategoryById(Long categoryId);
	boolean deleteCategory(Long categoryId);

}
