package com.alpine.service;

import com.alpine.domain.ShippingAddress;
import com.alpine.domain.UserShipping;

public interface ShippingAddressService {
    ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress);
}
