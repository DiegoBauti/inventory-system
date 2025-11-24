package com.inventory.inventory_api.Repository;

import java.util.List;
import java.util.Optional;

import com.inventory.inventory_api.Entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product,Integer> {
    List<Product> findByStatusTrue();
    Optional<Product> findByIdAndStatusTrue(int id);
    long countByCategoryId(int id);
    long countBySupplierId(int supplierId);
    boolean existsByName(String name);
}
