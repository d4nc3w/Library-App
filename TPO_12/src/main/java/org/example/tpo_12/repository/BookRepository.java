package org.example.tpo_12.repository;


import org.example.tpo_12.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {
}
