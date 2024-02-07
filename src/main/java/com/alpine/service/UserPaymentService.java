package com.alpine.service;

import com.alpine.domain.UserPayment;

public interface UserPaymentService {
    // Retrieves a user payment by its ID.
    UserPayment findById(Long id);
    // Retrieves a user payment by its ID.
    UserPayment save(UserPayment userPayment);
    // Removes a user payment by its ID.
    void removeById(Long id);
}
