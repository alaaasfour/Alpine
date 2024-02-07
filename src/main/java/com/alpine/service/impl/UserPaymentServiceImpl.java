package com.alpine.service.impl;

import com.alpine.domain.UserPayment;
import com.alpine.repository.UserPaymentRepository;
import com.alpine.service.UserPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPaymentServiceImpl implements UserPaymentService {
    @Autowired
    private UserPaymentRepository userPaymentRepository;
    // Retrieves a user payment by its ID.
    public UserPayment findById(Long id) {
        Optional<UserPayment> optionalUserPayment = userPaymentRepository.findById(id);
        return optionalUserPayment.orElse(null);
    }
    // Saves a user payment.
    public UserPayment save(UserPayment userPayment) {
        return userPaymentRepository.save(userPayment);
    }
    // Removes a user payment by its ID.
    public void removeById(Long id) {
        userPaymentRepository.deleteById(id);
    }
}
