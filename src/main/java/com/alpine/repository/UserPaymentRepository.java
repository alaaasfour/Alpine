package com.alpine.repository;

import com.alpine.domain.UserPayment;
import org.springframework.data.repository.CrudRepository;

public interface UserPaymentRepository extends CrudRepository<UserPayment, Long> {
    // Custom method to delete a UserPayment entity by its ID
    void deleteById(Long id);
}
