package dev.thubas.webshop.category;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {
	
	@Mock
	CategoryService categoryService;
	
	@InjectMocks
	CategoryController categoryController;
	
	List<Category> categories;
	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		categories = new ArrayList<>();
		categories.add(new Category(1L, "Cat1", "Description1", "image_url_1"));
		categories.add(new Category(2L, "Cat2", "Description2", "image_url_2"));
		
		mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
	}

	@Test
	void testCreateCategory() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCategories() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateCategory() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteCategory() {
		fail("Not yet implemented");
	}

}
