package com.alpine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.alpine.domain.Book;
public interface BookService {
    List<Book> findAll();
    Book findOne(Long id);
    Book save(Book book);
    List<Book> findByCategory(String category);
}

