package com.example.bookstore.controller;

import com.example.bookstore.generated.controller.BooksApiDelegate;
import com.example.bookstore.generated.dto.BookDTO;
import com.example.bookstore.service.BookDTOService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class BooksDelegate implements BooksApiDelegate {

    private final BookDTOService bookDTOService;

    @Override
    public ResponseEntity<List<BookDTO>> booksGet() {
        List<BookDTO> allBooks = bookDTOService.getAllBooks();
        return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> booksIsbnDelete(String isbn) {
        bookDTOService.deleteBook(isbn);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<BookDTO> booksIsbnGet(String isbn) {
        BookDTO bookByIsbn = bookDTOService.getBookByIsbn(isbn);
        return new ResponseEntity<>(bookByIsbn, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookDTO> booksIsbnPut(String isbn, BookDTO bookDTO) {
        bookDTOService.updateBook(bookDTO);
        return new ResponseEntity<>(bookDTOService.getBookByIsbn(isbn), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookDTO> booksPost(BookDTO bookDTO) {
        return new ResponseEntity<>(bookDTOService.createBook(bookDTO), HttpStatus.CREATED);
    }
}
