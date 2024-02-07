package com.alpine.service.impl;

import com.alpine.domain.UserShipping;
import com.alpine.repository.UserShippingRepository;
import com.alpine.service.UserShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserShippingServiceImpl implements UserShippingService {
    @Autowired
    private UserShippingRepository userShippingRepository;

    // Retrieves a user shipping information by its ID.
    public UserShipping findById(Long id) {
        Optional<UserShipping> optionalUserShipping = userShippingRepository.findById(id);
        return optionalUserShipping.orElse(null);
    }

    // Removes a user shipping information by its ID.
    public void removeById(Long id) {
        userShippingRepository.deleteById(id);
    }
}
