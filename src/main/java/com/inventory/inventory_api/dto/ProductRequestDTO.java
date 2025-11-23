package com.inventory.inventory_api.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDTO {
    private String name;
    private BigDecimal price;
    private Integer stock;
    private String description;
    private Integer categoryId;
    private Integer supplierId;
}
