package com.alpine.repository;
import org.springframework.data.repository.CrudRepository;
import com.alpine.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByCategory(String category);
    Optional<Book> findById(Long id);
}
