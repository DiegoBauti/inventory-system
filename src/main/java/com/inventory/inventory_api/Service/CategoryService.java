package com.inventory.inventory_api.Service;

import com.inventory.inventory_api.Entity.Category;
import com.inventory.inventory_api.Exception.BusinessException;
import com.inventory.inventory_api.Exception.ResourceNotFoundException;
import com.inventory.inventory_api.Mapper.CategoryMapper;
import com.inventory.inventory_api.Repository.CategoryRepository;
import com.inventory.inventory_api.Repository.ProductRepository;
import com.inventory.inventory_api.dto.CategoryRequestDTO;
import com.inventory.inventory_api.dto.CategoryResponseDTO;
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
    public CategoryResponseDTO save(CategoryRequestDTO categoryDto){
        Category category=CategoryMapper.toEntity(categoryDto);
        Category saved = categoryRepository.save(category);
        return CategoryMapper.toDTO(saved);
    }

    @Transactional
    public void delete(int id){
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoría no encontrada con ID: " + id);
        }

        long productsCount = productRepository.countByCategoryId(id);

        if (productsCount > 0) {
            throw new BusinessException(
                    "No se puede eliminar la categoría porque tiene " + productsCount +
                            " producto(s) asociado(s). Elimine o reasigne los productos primero."
            );
        }
        categoryRepository.deleteById(id);
    }

    @Transactional
    public Optional<CategoryResponseDTO> update(int id,CategoryRequestDTO categoryDto){
        Optional<Category> categoryOpt = categoryRepository.findById(id);
        if (categoryOpt.isPresent()){
            Category category=categoryOpt.get();

            if (categoryDto.getName() != null) {
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
