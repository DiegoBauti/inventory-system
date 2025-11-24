package com.inventory.inventory_api.Controller;

import com.inventory.inventory_api.Service.ProductService;
import com.inventory.inventory_api.dto.ProductCreateDTO;
import com.inventory.inventory_api.dto.ProductFilterDTO;
import com.inventory.inventory_api.dto.ProductResponseDTO;
import com.inventory.inventory_api.dto.ProductUpdateDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@Validated
public class ProductContoller {

    private final ProductService productService;

    public ProductContoller(ProductService productService){
        this.productService=productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        List<ProductResponseDTO> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable @Min(1) int id){
        Optional<ProductResponseDTO> productOpt=productService.findById(id);
        if (productOpt.isPresent()){
            return ResponseEntity.ok(productOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> save(@Valid @RequestBody ProductCreateDTO product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Min(1)  int id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable @Min(1) int id,@Valid @RequestBody ProductUpdateDTO productDto){
        Optional<ProductResponseDTO> updated = productService.update(id, productDto);

        if (updated.isPresent()) {
            return ResponseEntity.ok(updated.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDTO>> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) Integer supplierId,
            @RequestParam(required = false) String supplierName,
            @RequestParam(required = false) java.math.BigDecimal minPrice,
            @RequestParam(required = false) java.math.BigDecimal maxPrice
    ) {
        ProductFilterDTO filter = new ProductFilterDTO();
        filter.setName(name);
        filter.setCategoryId(categoryId);
        filter.setCategoryName(categoryName);
        filter.setSupplierId(supplierId);
        filter.setSupplierName(supplierName);
        filter.setMinPrice(minPrice);
        filter.setMaxPrice(maxPrice);

        List<ProductResponseDTO> results = productService.search(filter);
        return ResponseEntity.ok(results);
    }
}
