package com.alpine.repository;
import org.springframework.data.repository.CrudRepository;
import com.alpine.domain.Book;

import java.util.List;
import java.util.Optional;

/*
* Extends the Spring Data CrudRepository interface, specifying the entity type (Book) and the type of its primary key (Long).
* This provides basic CRUD operations (Create, Read, Update, Delete) for the Book entity.
* */
public interface BookRepository extends CrudRepository<Book, Long> {
    //  Method signature for finding books by category.
    List<Book> findByCategory(String category);

    //  Method signature for finding a book by its ID.
    Optional<Book> findById(Long id);

    // Method signature for finding books whose title contains a given substring.
    List<Book> findByTitleContaining(String title);
}
