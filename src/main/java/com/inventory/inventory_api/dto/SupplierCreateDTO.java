package com.inventory.inventory_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SupplierCreateDTO {

    @NotBlank(message = "Supplier name is required")
    @Size(min = 3, max = 50, message = "Supplier name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Supplier email is required")
    @Email(message = "Invalid email format")
    @Size(max = 50, message = "Email must not exceed 50 characters")
    private String email;

    @NotBlank(message = "Supplier phone is required")
    @Size(min = 9, max = 9, message = "Phone must be between 3 and 20 characters")
    private String phone;
}
