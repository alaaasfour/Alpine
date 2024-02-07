package com.alpine.repository;

import com.alpine.domain.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {
    // No additional methods are defined here because CRUD operations are provided by CrudRepository
    // Custom methods can be added if needed, but for basic CRUD operations, CrudRepository is sufficient
}
