package com.alpine.service;

import com.alpine.domain.Payment;
import com.alpine.domain.UserPayment;

public interface PaymentService {
    // Sets the payment details based on the user payment information.
    Payment setByUserPayment(UserPayment userPayment, Payment payment);
}
