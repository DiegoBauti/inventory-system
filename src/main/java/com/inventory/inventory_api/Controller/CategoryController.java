package com.inventory.inventory_api.Controller;

import com.inventory.inventory_api.Service.CategoryService;
import com.inventory.inventory_api.dto.CategoryCreateDTO;
import com.inventory.inventory_api.dto.CategoryResponseDTO;
import com.inventory.inventory_api.dto.CategoryUpdateDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@Validated
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> findAll() {
        List<CategoryResponseDTO> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> findById(@PathVariable @Min(1) int id){
        Optional<CategoryResponseDTO> categoryOpt=categoryService.findById(id);
        if (categoryOpt.isPresent()){
            return ResponseEntity.ok(categoryOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> save(@Valid @RequestBody CategoryCreateDTO category){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Min(1) int id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> update(@PathVariable @Min(1) int id,
                                                      @Valid @RequestBody CategoryUpdateDTO categoryDto){
        Optional<CategoryResponseDTO> updated = categoryService.update(id, categoryDto);
        if (updated.isPresent()) {
            return ResponseEntity.ok(updated.get());
        }
        return ResponseEntity.notFound().build();
    }
}
