package com.inventory.inventory_api.Mapper;

import com.inventory.inventory_api.Entity.Product;
import com.inventory.inventory_api.dto.*;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {
    public static ProductResponseDTO toDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setDescription(product.getDescription());
        return dto;
    }

    public static List<ProductResponseDTO> toDTOList(List<Product> products) {
        List<ProductResponseDTO> dtos = new ArrayList<>();
        for (Product product : products) {
            dtos.add(toDTO(product));
        }
        return dtos;
    }

    public static Product toEntity(ProductRequestDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setDescription(dto.getDescription());
        return product;
    }
}
