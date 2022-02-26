package com.example.intellijws.repo;

import com.example.intellijws.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepo extends CrudRepository<Book, Integer> {
}
