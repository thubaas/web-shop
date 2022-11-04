package dev.thubas.webshop.category;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.thubas.webshop.exception.CategoryExistsException;
import dev.thubas.webshop.exception.CategoryNotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private final Logger log = LoggerFactory
			.getLogger(CategoryServiceImpl.class);
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public boolean existsByName(String name) {
		return categoryRepository.existsByName(name);
	}

	@Override
	public boolean existsById(Long id) {
		return categoryRepository.existsById(id);
	}

	@Override
	public Category createCategory(Category category) {
		if(existsByName(category.getName())) {
			String msg = new String(
					"Category '" + category.getName() 
					+ "' exists already");
			throw new CategoryExistsException(msg);
			
		}
			
		Category savedCategory = categoryRepository.save(category);
		log.info("Saved Category :{}", savedCategory);
		return savedCategory;
	}

	@Override
	public Category updateCategory(Long categoryId, Category category) {
		log.info("Category Update : {}" + category);
		if(!existsById(categoryId)) {
			String msg = new String(
					"Category '" + category.getName() 
					+ "' not found");
			throw new CategoryNotFoundException(msg);
		}
		
		Category targetCategory = categoryRepository.findById(categoryId).get();
		targetCategory.setName(category.getName());
		targetCategory.setImageUrl(category.getImageUrl());
		targetCategory.setDescription(category.getDescription());
		return categoryRepository.save(targetCategory);
		
	}

	@Override
	public Category getCategoryByName(String name) {

		if(!existsByName(name)) {
			String msg = new String("Category '" + name + "' not found");
			throw new CategoryNotFoundException(msg);
		}
		return categoryRepository.findByName(name);
	}

	@Override
	public List<Category> getCategories() {
		List<Category> categories = categoryRepository.findAll();
		return categories;
	}

	@Override
	public Category getCategoryById(Long categoryId) {
		String msg = "Category not found";
		Category category = categoryRepository
				.findById(categoryId)
				.orElseThrow(() -> new CategoryNotFoundException(msg));
		return category;
	}

	@Override
	public boolean deleteCategory(Long categoryId) {
		categoryRepository.deleteById(categoryId);
		return true;
	}

}
