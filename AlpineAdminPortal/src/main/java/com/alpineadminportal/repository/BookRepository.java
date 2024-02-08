package com.alpineadminportal.repository;

import com.alpineadminportal.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

}
