package com.alpine.repository;
import org.springframework.data.repository.CrudRepository;
import com.alpine.domain.Book;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    Optional<Book> findById(Long id);
}
