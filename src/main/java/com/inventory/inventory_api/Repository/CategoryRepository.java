package com.inventory.inventory_api.Repository;

import com.inventory.inventory_api.Entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Integer> {
}
