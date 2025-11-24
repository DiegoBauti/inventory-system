package com.inventory.inventory_api.Mapper;

import com.inventory.inventory_api.Entity.Category;
import com.inventory.inventory_api.Dto.CategoryCreateDTO;
import com.inventory.inventory_api.Dto.CategoryResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {

    public static CategoryResponseDTO toDTO(Category category) {
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }

    public static List<CategoryResponseDTO> toDTOList(List<Category> categories) {
        List<CategoryResponseDTO> dtos = new ArrayList<>();
        for (Category category : categories) {
            dtos.add(toDTO(category));
        }
        return dtos;
    }

    public static Category toEntity(CategoryCreateDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return category;
    }

}
