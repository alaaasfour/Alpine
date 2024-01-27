package com.alpine.repository;

import com.alpine.domain.BookToCartItem;
import org.springframework.data.repository.CrudRepository;

public interface BookToCartItemRepository extends CrudRepository<BookToCartItem, Long> {
}
