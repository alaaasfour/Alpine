package com.alpine.repository;

import com.alpine.domain.CartItem;
import com.alpine.domain.Order;
import com.alpine.domain.ShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CartItemRepository extends CrudRepository<CartItem, Long> {
    // Finds all cart items associated with the given shopping cart.
    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
    // Finds all cart items associated with the given order.
    List<CartItem> findByOrder(Order order);
}
