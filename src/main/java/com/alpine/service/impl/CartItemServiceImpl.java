package com.alpine.service.impl;

import com.alpine.domain.CartItem;
import com.alpine.domain.ShoppingCart;
import com.alpine.repository.CartItemRepository;
import com.alpine.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;
    public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
        return cartItemRepository.findByShoppingCart(shoppingCart);
    }
}
