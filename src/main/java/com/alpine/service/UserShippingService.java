package com.alpine.service;

import com.alpine.domain.UserShipping;

public interface UserShippingService {
    UserShipping findById(Long id);
    void removeById(Long id);
}
