package com.alpine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.alpine.domain.Book;
public interface BookService {
    // Retrieves all books.
    List<Book> findAll();
    // Retrieves a book by its ID.
    Book findOne(Long id);
    // Saves a book.
    Book save(Book book);
    // Retrieves all books belonging to a specific category.
    List<Book> findByCategory(String category);
    // Performs a blurry search based on the title of the books.
    List<Book> blurrySearch(String title);
}

