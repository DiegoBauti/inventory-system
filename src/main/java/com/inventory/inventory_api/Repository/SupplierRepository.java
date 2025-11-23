package com.inventory.inventory_api.Repository;

import com.inventory.inventory_api.Entity.Supplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier,Integer> {
}
