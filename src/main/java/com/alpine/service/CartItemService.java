package com.alpine.service;

import com.alpine.domain.Book;
import com.alpine.domain.CartItem;
import com.alpine.domain.ShoppingCart;
import com.alpine.domain.User;

import java.util.List;

public interface CartItemService {
    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
    CartItem updateCartItem(CartItem cartItem);
    CartItem addBookToCartItem(Book book, User user, int qty);
    CartItem findById(Long id);
    void removeCartItem(CartItem cartItem);
    CartItem save(CartItem cartItem);
}
