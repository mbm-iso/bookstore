package com.example.bookstore.controller;

import com.example.bookstore.generated.controller.BooksApiDelegate;
import com.example.bookstore.generated.dto.BookDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BooksDelegate implements BooksApiDelegate {

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<List<BookDTO>> booksGet() {
        return new ResponseEntity<>(new ArrayList<BookDTO>(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> booksIsbnDelete(String isbn) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookDTO> booksIsbnGet(String isbn) {
        return new ResponseEntity<>(new BookDTO(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> booksIsbnPut(String isbn, BookDTO bookDTO) {
        return BooksApiDelegate.super.booksIsbnPut(isbn, bookDTO);
    }

    @Override
    public ResponseEntity<Void> booksPost(BookDTO bookDTO) {
        return BooksApiDelegate.super.booksPost(bookDTO);
    }
}
