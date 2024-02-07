package com.alpine.repository;

import org.springframework.data.repository.CrudRepository;
import com.alpine.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
    // Method to find a Role by its name
    Role findByName(String name);
}
