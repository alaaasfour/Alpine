package com.alpine.service;

import com.alpine.domain.BillingAddress;
import com.alpine.domain.UserBilling;

public interface BillingAddressService {
    /**
     * Sets the billing address based on the user billing information.
     *
     * @param userBilling    The user's billing information.
     * @param billingAddress The billing address to be set.
     * @return The updated billing address after setting it based on the user billing information.
     */
    BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress);
}
