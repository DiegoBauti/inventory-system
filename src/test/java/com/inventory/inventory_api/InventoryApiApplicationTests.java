package com.inventory.inventory_api;

import com.inventory.inventory_api.Dto.CategoryCreateDTO;
import com.inventory.inventory_api.Exception.BusinessException;
import com.inventory.inventory_api.Repository.CategoryRepository;
import com.inventory.inventory_api.Repository.ProductRepository;
import com.inventory.inventory_api.Service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

@SpringBootTest
class InventoryApiApplicationTests {

	@Mock
	private CategoryRepository categoryRepository;

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private CategoryService categoryService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	//TEST 01
	@Test
	void testSave_CategoryNameAlreadyExists_ThrowsBusinessException() {
		CategoryCreateDTO dto = new CategoryCreateDTO();
		dto.setName("Electronics");

		when(categoryRepository.existsByName("Electronics")).thenReturn(true);

		assertThrows(BusinessException.class, () -> categoryService.save(dto));

		verify(categoryRepository, never()).save(any());
	}

	//TEST 02
	@Test
	void testDelete_CategoryHasProducts_ThrowsBusinessException() {
		int categoryId = 1;

		when(categoryRepository.existsById(categoryId)).thenReturn(true);
		when(productRepository.countByCategoryId(categoryId)).thenReturn(5L);

		assertThrows(BusinessException.class, () -> categoryService.delete(categoryId));

		verify(categoryRepository, never()).deleteById(categoryId);
	}


}
