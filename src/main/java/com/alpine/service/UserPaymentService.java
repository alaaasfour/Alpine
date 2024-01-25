package com.alpine.service;

import com.alpine.domain.UserPayment;

public interface UserPaymentService {
    UserPayment findById(Long id);
    UserPayment save(UserPayment userPayment);
}
