package com.inventory.inventory_api.Repository;

import com.inventory.inventory_api.Entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role,Integer> {
}
