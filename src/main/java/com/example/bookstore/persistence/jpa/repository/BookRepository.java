package com.example.bookstore.persistence.jpa.repository;

import com.example.bookstore.model.Author;
import com.example.bookstore.persistence.jpa.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    Optional<BookEntity> findByIsbn(String isbn);
}
