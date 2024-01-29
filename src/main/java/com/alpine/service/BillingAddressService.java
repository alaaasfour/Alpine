package com.alpine.service;

import com.alpine.domain.BillingAddress;
import com.alpine.domain.UserBilling;

public interface BillingAddressService {
    BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress);
}
