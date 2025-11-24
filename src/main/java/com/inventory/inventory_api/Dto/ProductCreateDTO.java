package com.inventory.inventory_api.Dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductCreateDTO {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name must have between 3 and 100 characters")
    private String name;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "1.00", message = "Price must be greater than 0")
    private BigDecimal price;

    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock must be equal or greater than 0")
    private Integer stock;

    @NotBlank(message = "Description is required")
    @Size(max = 300, message = "Description must not exceed 300 characters")
    private String description;

    @NotNull(message = "Category ID is required")
    @Positive(message = "Category ID must be a positive number")
    private Integer categoryId;

    @NotNull(message = "Supplier ID is required")
    @Positive(message = "Supplier ID must be a positive number")
    private Integer supplierId;
}
