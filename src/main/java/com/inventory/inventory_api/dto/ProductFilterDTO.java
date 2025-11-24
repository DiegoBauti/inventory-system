package com.inventory.inventory_api.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductFilterDTO {
    private String name;
    private Integer categoryId;
    private String categoryName;
    private Integer supplierId;
    private String supplierName;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
}
