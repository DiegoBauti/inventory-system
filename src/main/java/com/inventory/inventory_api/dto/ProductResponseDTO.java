package com.inventory.inventory_api.dto;

import jakarta.persistence.Column;
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
