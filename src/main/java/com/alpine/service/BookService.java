package com.alpine.service;

import java.util.List;
import com.alpine.domain.Book;

public interface BookService {
    List<Book> findAll();
}
