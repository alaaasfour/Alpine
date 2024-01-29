package com.alpine.service;

import com.alpine.domain.Payment;
import com.alpine.domain.UserPayment;

public interface PaymentService {
    Payment setByUserPayment(UserPayment userPayment, Payment payment);
}
