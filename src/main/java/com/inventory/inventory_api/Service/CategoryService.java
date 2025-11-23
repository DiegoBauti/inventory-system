package com.inventory.inventory_api.Service;

import com.inventory.inventory_api.Entity.Category;
import com.inventory.inventory_api.Exception.BusinessException;
import com.inventory.inventory_api.Exception.ResourceNotFoundException;
import com.inventory.inventory_api.Mapper.CategoryMapper;
import com.inventory.inventory_api.Repository.CategoryRepository;
import com.inventory.inventory_api.Repository.ProductRepository;
import com.inventory.inventory_api.dto.CategoryCreateDTO;
import com.inventory.inventory_api.dto.CategoryResponseDTO;
import com.inventory.inventory_api.dto.CategoryUpdateDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository,ProductRepository productRepository){
        this.categoryRepository=categoryRepository;
        this.productRepository=productRepository;
    }

    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> findAll(){
        List<Category> categories=(List<Category>) categoryRepository.findAll();
        return CategoryMapper.toDTOList(categories);
    }

    @Transactional(readOnly = true)
    public Optional<CategoryResponseDTO> findById(int id){
        Optional<Category> categoryOpt=categoryRepository.findById(id);
        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();
            CategoryResponseDTO dto = CategoryMapper.toDTO(category);
            return Optional.of(dto);
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public CategoryResponseDTO save(CategoryCreateDTO categoryDto){
        if (categoryRepository.existsByName(categoryDto.getName())) {
            throw new BusinessException("There is already a category with that name");
        }

        Category category = CategoryMapper.toEntity(categoryDto);
        Category saved = categoryRepository.save(category);
        return CategoryMapper.toDTO(saved);
    }

    @Transactional
    public void delete(int id){
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found with ID: " + id);
        }

        long productsCount = productRepository.countByCategoryId(id);

        if (productsCount > 0) {
            throw new BusinessException(
                    "The category cannot be deleted because it has " + productsCount +
                    " Associated product. Remove or assign the products first"
            );
        }
        categoryRepository.deleteById(id);
    }

    @Transactional
    public Optional<CategoryResponseDTO> update(int id, CategoryUpdateDTO categoryDto){
        Optional<Category> categoryOpt = categoryRepository.findById(id);
        if (categoryOpt.isPresent()){

            Category category=categoryOpt.get();

            if (categoryDto.getName() != null && !category.getName().equalsIgnoreCase(categoryDto.getName())) {
                if (categoryRepository.existsByName(categoryDto.getName())) {
                    throw new BusinessException("There is already a category with that name");
                }
                category.setName(categoryDto.getName());
            }

            if (categoryDto.getDescription() != null) {
                category.setDescription(categoryDto.getDescription());
            }

            Category updated=categoryRepository.save(category);
            return Optional.of(CategoryMapper.toDTO(updated));
        }
        return Optional.empty();
    }
}
