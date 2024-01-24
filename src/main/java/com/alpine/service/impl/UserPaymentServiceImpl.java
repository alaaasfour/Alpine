package com.alpine.service.impl;

import com.alpine.repository.UserPaymentRepository;
import com.alpine.service.UserPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPaymentServiceImpl implements UserPaymentService {
    @Autowired
    private UserPaymentRepository userPaymentRepository;
}
