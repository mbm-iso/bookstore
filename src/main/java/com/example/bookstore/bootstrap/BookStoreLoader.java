package com.example.bookstore.bootstrap;

import com.example.bookstore.generated.dto.BookDTO;
import com.example.bookstore.mapper.BookDTOMapper;
import com.example.bookstore.model.Author;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Chapter;
import com.example.bookstore.persistence.servive.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Component
@Profile("h2db") // Profil um h2 init Daten nutzen zu können
public class BookStoreLoader implements CommandLineRunner {

    private final BookService bookService;
    private final BookDTOMapper bookDTOMapper;

    @Override
    public void run(String... args) throws Exception {

        bookService.createBook(book1());
        bookService.createBook(book2());
    }

    private Book book1() {
        Author author = Author.builder()
                .firstName("Martin")
                .lastName("Fowler")
                .email("mfowler@example.com")
                .biography("Author and software architect.")
                .birthDate(LocalDate.of(1963, 12, 18))
                .build();

        Chapter chapter1 = Chapter.builder()
                .number(1)
                .title("Intro")
                .summary("Summary 1")
                .build();

        Chapter chapter2 = Chapter.builder()
                .number(2)
                .title("Design")
                .summary("Summary 2")
                .build();

        Book book = Book.builder()
                .isbn("123-456")
                .title("Refactoring")
                .published(true)
                .author(author)
                .chapters(List.of(chapter1, chapter2))
                .build();

        return book;
    }

    private Book book2() {
        Author author = Author.builder()
                .firstName("John")
                .lastName("Braun")
                .email("kohn.braun@example.com")
                .biography("A very famous Author")
                .birthDate(LocalDate.of(1975, 2, 10))
                .build();

        Chapter chapter1 = Chapter.builder()
                .number(1)
                .title("Intro one")
                .summary("Summary one")
                .build();

        Chapter chapter2 = Chapter.builder()
                .number(2)
                .title("Chapter tow")
                .summary("Summary 2")
                .build();

        Book book = Book.builder()
                .isbn("133-447")
                .title("The new Roman")
                .published(false)
                .author(author)
                .chapters(List.of(chapter1, chapter2))
                .build();

        return book;
    }
}
