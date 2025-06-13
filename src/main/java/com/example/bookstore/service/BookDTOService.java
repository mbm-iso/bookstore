package com.example.bookstore.service;

import com.example.bookstore.generated.dto.BookDTO;
import com.example.bookstore.mapper.BookDTOMapper;
import com.example.bookstore.model.Book;
import com.example.bookstore.persistence.servive.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookDTOService {

    private final BookService bookService;
    private final BookDTOMapper bookDTOMapper;

    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks().stream().
                map(bookDTOMapper::bookToBookDTO)
                .collect(Collectors.toList());
    }

    public BookDTO getBookByIsbn(String isbn) {
        Book bookByIsbn = bookService.getBookByIsbn(isbn);
        return bookDTOMapper.bookToBookDTO(bookByIsbn);
    }

    public BookDTO createBook(BookDTO bookDTO) {
        Book createdBook = bookService.createBook(bookDTOMapper.bookDTOToBook(bookDTO));
        return bookDTOMapper.bookToBookDTO(createdBook);
    }

    public void updateBook(BookDTO book) {
        Book updateBook = bookService.updateBook(bookDTOMapper.bookDTOToBook(book));
        bookDTOMapper.bookToBookDTO(updateBook);
    }

    public void deleteBook(String isbn) {
        bookService.deleteBook(isbn);
    }
}
