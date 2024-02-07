package com.alpine.service;

import com.alpine.domain.UserShipping;

public interface UserShippingService {
    // // Retrieves a user shipping information by its ID.
    UserShipping findById(Long id);

    // Removes a user shipping information by its ID.
    void removeById(Long id);
}
