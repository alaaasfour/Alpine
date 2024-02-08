package com.alpineadminportal.service;

import com.alpineadminportal.domain.Book;

import java.util.List;

public interface BookService {
    Book save(Book book);
    List<Book> findAll();
    Book findOne(Long id);
    void removeOne(Long id);
}
