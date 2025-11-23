package com.inventory.inventory_api.dto;

import lombok.Data;

@Data
public class SupplierResponseDTO {
    private int id;
    private String name;
    private String email;
    private String phone;
}
