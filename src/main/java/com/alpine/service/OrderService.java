package com.alpine.service;

import com.alpine.domain.*;

public interface OrderService {
    // Creates an order with the specified details.
    Order createOrder(ShoppingCart shoppingCart,
                      ShippingAddress shippingAddress,
                      BillingAddress billingAddress,
                      Payment payment,
                      String shippingMethod,
                      User user);

    // Retrieves an order by its ID.
    Order findOne(Long id);
}
