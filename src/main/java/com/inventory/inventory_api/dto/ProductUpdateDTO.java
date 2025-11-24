package com.inventory.inventory_api.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductUpdateDTO {

    @Size(min = 3, max = 100, message = "Name must have between 3 and 100 characters")
    private String name;

    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private BigDecimal price;

    @Min(value = 0, message = "Stock must be equal or greater than 0")
    private Integer stock;

    @Size(max = 300, message = "Description must not exceed 300 characters")
    private String description;

    @Positive(message = "Category ID must be a positive number")
    private Integer categoryId;

    @Positive(message = "Supplier ID must be a positive number")
    private Integer supplierId;
}
