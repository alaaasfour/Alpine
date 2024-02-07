package com.alpine.service;

import com.alpine.domain.ShippingAddress;
import com.alpine.domain.UserShipping;

public interface ShippingAddressService {
    // Sets the shipping address based on the user shipping information.
    ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress);
}
