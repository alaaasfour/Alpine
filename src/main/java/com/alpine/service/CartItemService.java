package com.alpine.service;

import com.alpine.domain.*;

import java.util.List;

public interface CartItemService {
    // Retrieves all cart items associated with a shopping cart.
    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
    // Updates a book to the cart item associated with a user.
    CartItem updateCartItem(CartItem cartItem);
    // Adds a book to the cart item associated with a user.
    CartItem addBookToCartItem(Book book, User user, int qty);
    // Retrieves a cart item by its ID.
    CartItem findById(Long id);
    // Removes a cart item.
    void removeCartItem(CartItem cartItem);
    CartItem save(CartItem cartItem);
    // Retrieves all cart items associated with an order.
    List<CartItem> findByOrder(Order order);
}
