package com.inventory.inventory_api.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryUpdateDTO {
    @Size(min = 3, max = 50)
    private String name;

    @Size(min = 10,max = 200)
    private String description;
}
