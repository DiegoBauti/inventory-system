package com.inventory.inventory_api.Repository;

import java.util.Optional;

import com.inventory.inventory_api.Entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product,Integer>, JpaSpecificationExecutor<Product> {
    Page<Product> findByStatusTrue(Pageable pageable);
    Optional<Product> findByIdAndStatusTrue(int id);
    long countByCategoryId(int id);
    long countBySupplierId(int supplierId);
    boolean existsByName(String name);
}
