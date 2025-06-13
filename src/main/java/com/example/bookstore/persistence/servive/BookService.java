package com.example.bookstore.persistence.servive;


import com.example.bookstore.model.Book;
import com.example.bookstore.persistence.jpa.entity.BookEntity;
import com.example.bookstore.persistence.jpa.exeption.EntityNotFoundException;
import com.example.bookstore.persistence.mapper.BookEntityMapper;
import com.example.bookstore.persistence.jpa.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookEntityMapper bookEntityMapper;

    public List<Book> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookEntityMapper::bookEntityToBook)
                .collect(Collectors.toList());
    }

    public Book getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .map(bookEntityMapper::bookEntityToBook)
                .orElseThrow(() -> new EntityNotFoundException("BookEntity",isbn));
    }

    @Transactional
    public Book createBook(Book book) {
        log.debug("Creating new book {}", book);
        BookEntity entity = bookEntityMapper.bookToBookEntity(book);
        BookEntity savedEntity = bookRepository.save(entity);

        return bookEntityMapper.bookEntityToBook(savedEntity);
    }

    @Transactional
    public Book updateBook(Book book) {
        log.debug("Update book {}", book);
        BookEntity bookEntity = bookRepository.findByIsbn(book.getIsbn())
                .orElseThrow(() -> new EntityNotFoundException("BookEntity", book.getIsbn()));
        BookEntity updatedEntity = bookEntityMapper.updateBookEntity(bookEntity, book);

        return bookEntityMapper.bookEntityToBook(bookRepository.save(updatedEntity));
    }

    @Transactional
    public void deleteBook(String isbn) {
        log.debug("Delete book with ISBN {}", isbn);
        BookEntity bookEntity = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new EntityNotFoundException("BookEntity", isbn));
        bookRepository.deleteById(bookEntity.getId());
    }
}
