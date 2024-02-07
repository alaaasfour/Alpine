package com.alpine.repository;

import com.alpine.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
    // This interface inherits basic CRUD operations from CrudRepository and does not define any additional custom methods.
}
