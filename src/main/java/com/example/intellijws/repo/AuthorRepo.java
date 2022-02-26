package com.example.intellijws.repo;

import com.example.intellijws.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepo extends CrudRepository<Author, Integer> {
}
