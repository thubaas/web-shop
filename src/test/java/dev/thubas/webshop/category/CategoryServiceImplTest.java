package dev.thubas.webshop.category;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {
	
	static final Long ID = 1L;
	static final String NAME = "Cat1";
	static final String DESCRIPTION = "Descritpion1";
	static final String IMAGE_URL = "Image_Url1";
	
	@Mock
	CategoryRepository categoryRepository;
	
	@InjectMocks
	CategoryServiceImpl categoryService;
	
	Category category;

	@BeforeEach
	void setUp() throws Exception {
		categoryService = new CategoryServiceImpl();
		category = new Category(NAME, DESCRIPTION, IMAGE_URL);
	}

	@Test
	@Disabled
	void testExistsByName() {
		fail("Not yet implemented");
//		assertTrue(categoryService.existsByName(NAME));
	}

	@Disabled
	@Test
	void testExistsById() {
		fail("Not yet implemented");
	}

	@Test
	void testCreateCategory() {
		when(categoryRepository.save(any(Category.class)))
		.thenReturn(category);

		Category savedCategory = categoryService.createCategory(category);
		assertEquals(savedCategory.getName(), NAME);
	}

	@Disabled
	@Test
	void testUpdateCategory() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	void testGetCategoryByName() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	void testGetCategories() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	void testGetCategoryById() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	void testDeleteCategory() {
		fail("Not yet implemented");
	}

}
