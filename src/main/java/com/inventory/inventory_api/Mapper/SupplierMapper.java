package com.inventory.inventory_api.Mapper;

import com.inventory.inventory_api.Entity.Supplier;
import com.inventory.inventory_api.dto.SupplierRequestDTO;
import com.inventory.inventory_api.dto.SupplierResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class SupplierMapper {
    public static SupplierResponseDTO toDTO(Supplier supplier) {
        SupplierResponseDTO dto = new SupplierResponseDTO();
        dto.setId(supplier.getId());
        dto.setName(supplier.getName());
        dto.setPhone(supplier.getPhone());
        dto.setEmail(supplier.getEmail());
        return dto;
    }

    public static List<SupplierResponseDTO> toDTOList(List<Supplier> suppliers) {
        List<SupplierResponseDTO> dtos = new ArrayList<>();
        for (Supplier supplier : suppliers) {
            dtos.add(toDTO(supplier));
        }
        return dtos;
    }

    public static Supplier toEntity(SupplierRequestDTO dto) {
        Supplier supplier = new Supplier();
        supplier.setName(dto.getName());
        supplier.setPhone(dto.getPhone());
        supplier.setEmail(dto.getEmail());
        return supplier;
    }
}
