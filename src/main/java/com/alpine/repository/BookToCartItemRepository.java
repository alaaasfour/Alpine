package com.alpine.repository;

import com.alpine.domain.BookToCartItem;
import com.alpine.domain.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BookToCartItemRepository extends CrudRepository<BookToCartItem, Long> {
    // Deletes the BookToCartItem entries associated with the given CartItem.
    void deleteByCartItem(CartItem cartItem);
}
