package com.inventory.inventory_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryCreateDTO {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank(message = "The description is required")
    @Size(min = 10,max = 200)
    private String description;
}