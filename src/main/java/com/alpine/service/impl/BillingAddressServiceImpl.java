package com.alpine.service.impl;

import com.alpine.domain.BillingAddress;
import com.alpine.domain.UserBilling;
import com.alpine.service.BillingAddressService;
import org.springframework.stereotype.Service;

@Service
public class BillingAddressServiceImpl implements BillingAddressService {
    public BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress) {
        billingAddress.setBillingAddressName(userBilling.getUserBillingName());
        billingAddress.setBillingAddressStreet1(userBilling.getUserBillingStreet1());
        billingAddress.setBillingAddressStreet2(userBilling.getUserBillingStreet2());
        billingAddress.setBillingAddressCity(userBilling.getUserBillingCity());
        billingAddress.setBillingAddressStateProvince(userBilling.getUserBillingStateProvince());
        billingAddress.setBillingAddressZipPostalCode(userBilling.getUserBillingZipPostalCode());
        billingAddress.setBillingAddressCountry(userBilling.getUserBillingCountry());

        return billingAddress;
    }
}
