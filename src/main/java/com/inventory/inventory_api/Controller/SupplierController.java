package com.inventory.inventory_api.Controller;

import com.inventory.inventory_api.Service.SupplierService;
import com.inventory.inventory_api.dto.SupplierCreateDTO;
import com.inventory.inventory_api.dto.SupplierResponseDTO;
import com.inventory.inventory_api.dto.SupplierUpdateDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/supplier")
@Validated
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService){
        this.supplierService=supplierService;
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponseDTO>> findAll() {
        List<SupplierResponseDTO> suppliers = supplierService.findAll();
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> findById(@PathVariable @Min(1)  int id){
        Optional<SupplierResponseDTO> supplierOpt=supplierService.findById(id);
        if (supplierOpt.isPresent()){
            return ResponseEntity.ok(supplierOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<SupplierResponseDTO> save(@Valid @RequestBody SupplierCreateDTO supplier){
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierService.save(supplier));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Min(1) int id) {
        supplierService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @PatchMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> update(@PathVariable @Min(1) int id,@Valid @RequestBody SupplierUpdateDTO supplierDto){
        Optional<SupplierResponseDTO> updated = supplierService.update(id, supplierDto);

        if (updated.isPresent()) {
            return ResponseEntity.ok(updated.get());
        }
        return ResponseEntity.notFound().build();
    }
}
