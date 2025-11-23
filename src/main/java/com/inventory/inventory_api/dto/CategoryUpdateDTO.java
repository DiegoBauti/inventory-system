package com.inventory.inventory_api.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryUpdateDTO {
    @Size(min = 3, max = 50,message = "Name must be between 3 and 50 characters")
    private String name;

    @Size(min = 10,max = 200,message = "Description must be between 10 and 200 characters")
    private String description;
}
