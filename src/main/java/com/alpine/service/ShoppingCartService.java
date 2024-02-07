package com.alpine.service;

import com.alpine.domain.ShoppingCart;

public interface ShoppingCartService {
    // Updates the shopping cart.
    ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);
    // Clears the items in the shopping cart.
    void clearShoppingCart(ShoppingCart shoppingCart);
}
