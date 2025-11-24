package com.inventory.inventory_api.Dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponseDTO {
    private int id;
    private String name;
    private BigDecimal price;
    private int stock;
    private String description;
}
