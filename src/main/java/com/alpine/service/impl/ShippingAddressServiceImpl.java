package com.alpine.service.impl;

import com.alpine.domain.ShippingAddress;
import com.alpine.domain.UserShipping;
import com.alpine.service.ShippingAddressService;
import org.springframework.stereotype.Service;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {
    public ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress) {
        shippingAddress.setShippingAddressName(userShipping.getUserShippingName());
        shippingAddress.setShippingAddressStreet1(userShipping.getUserShippingStreet1());
        shippingAddress.setShippingAddressStreet2(userShipping.getUserShippingStreet2());
        shippingAddress.setShippingAddressCity(userShipping.getUserShippingCity());
        shippingAddress.setShippingAddressStateProvince(userShipping.getUserShippingStateProvince());
        shippingAddress.setShippingAddressZipPostalCode(userShipping.getUserShippingZipPostalCode());
        shippingAddress.setShippingAddressCountry(userShipping.getUserShippingCountry());

        return shippingAddress;
    }
}
