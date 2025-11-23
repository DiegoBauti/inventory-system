package com.inventory.inventory_api.Controller;

import com.inventory.inventory_api.Service.ProductService;
import com.inventory.inventory_api.dto.ProductRequestDTO;
import com.inventory.inventory_api.dto.ProductResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
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
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable int id){
        Optional<ProductResponseDTO> productOpt=productService.findById(id);
        if (productOpt.isPresent()){
            return ResponseEntity.ok(productOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> save(@RequestBody ProductRequestDTO product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        Optional<ProductResponseDTO> product=productService.delete(id);
        if (product.isPresent()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable int id,@RequestBody ProductRequestDTO productDto){
        Optional<ProductResponseDTO> updated = productService.update(id, productDto);

        if (updated.isPresent()) {
            return ResponseEntity.ok(updated.get());
        }
        return ResponseEntity.notFound().build();
    }
}
