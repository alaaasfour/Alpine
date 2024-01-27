package com.alpine.service;

import com.alpine.domain.CartItem;
import com.alpine.domain.ShoppingCart;

import java.util.List;

public interface CartItemService {
    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
}
