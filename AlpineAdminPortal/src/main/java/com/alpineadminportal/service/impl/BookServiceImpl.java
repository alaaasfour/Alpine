package com.alpineadminportal.service.impl;

import com.alpineadminportal.domain.Book;
import com.alpineadminportal.repository.BookRepository;
import com.alpineadminportal.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book save(Book book) {
        return bookRepository.save(book);
    }
    public List<Book> findAll() {
        return (List<Book>) bookRepository.findAll();
    }
    public Book findOne(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.orElse(null);
    }
    public void removeOne(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        optionalBook.ifPresent(book -> bookRepository.delete(book));
    }
}
