package com.example.bookstore.mapper;

import com.example.bookstore.generated.dto.AuthorDTO;
import com.example.bookstore.generated.dto.BookDTO;
import com.example.bookstore.generated.dto.ChapterDTO;
import com.example.bookstore.model.Author;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Chapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BookDTOMapper Tests - ")
class BookDTOMapperTest {

    BookDTOMapper bookDTOMapper = new BookDTOMapperImpl();

    @Test
    void bookDTOToBook() {
        AuthorDTO fowler = new AuthorDTO()
                .firstName("Martin")
                .lastName("Fowler")
                .birthDate(LocalDate.of(1963, 12, 18))
                .email("mfowler@example.com")
                .biography("Martin Fowler is a renowned software engineer, author, and speaker specializing in software architecture,\n" +
                        "    domain-specific languages, and agile methodologies. He is a Chief Scientist at ThoughtWorks and has\n" +
                        "    written several influential books such as \"Refactoring\" and \"Patterns of Enterprise Application Architecture\".\n");

        BookDTO bookDTO = new BookDTO();
        bookDTO.setIsbn("123");
        bookDTO.setAuthor(fowler);
        bookDTO.setTitle("The Story");

        ChapterDTO c1 = new ChapterDTO()
                .number(1)
                .title("Chapter 1")
                .summary("Summary 1");

        ChapterDTO c2 = new ChapterDTO()
                .number(2)
                .title("Chapter 2")
                .summary("Summary 3");

        ChapterDTO c3 = new ChapterDTO()
                .number(1)
                .title("Chapter 3")
                .summary("Summary 3");
        bookDTO.addChaptersItem(c1);
        bookDTO.addChaptersItem(c2);
        bookDTO.addChaptersItem(c3);

        Book book = bookDTOMapper.bookDTOToBook(bookDTO);

        assertEquals(bookDTO.getIsbn(), book.getIsbn());
        assertEquals(bookDTO.getTitle(), book.getTitle());
        assertEquals(bookDTO.getChapters().size(), book.getChapters().size());
    }

    @Test
    void bookToBookDTO() {
        Author author = Author.builder()
                .firstName("Martin")
                .lastName("Fowler")
                .email("mfowler@example.com")
                .biography("Martin Fowler is a renowned software engineer, author, and speaker specializing in software architecture,\n" +
                        "domain-specific languages, and agile methodologies. He is a Chief Scientist at ThoughtWorks and has\n" +
                        "written several influential books such as \"Refactoring\" and \"Patterns of Enterprise Application Architecture\".\n")
                .birthDate(LocalDate.of(1963, 12, 18))
                .build();

        Chapter ch1 = Chapter.builder()
                .number(1)
                .title("Chapter 1")
                .summary("Summary 1")
                .build();

        Chapter ch2 = Chapter.builder()
                .number(2)
                .title("Chapter 2")
                .summary("Summary 3")
                .build();

        Chapter ch3 = Chapter.builder()
                .number(1)
                .title("Chapter 3")
                .summary("Summary 3")
                .build();

        Book book = Book.builder()
                .isbn("123")
                .title("The Story")
                .author(author)
                .chapters(List.of(ch1, ch2, ch3))
                .build();

        BookDTO bookDTO = bookDTOMapper.bookToBookDTO(book);

        assertEquals(book.getIsbn(), bookDTO.getIsbn());
        assertEquals(book.getTitle(), bookDTO.getTitle());

        assertNotNull(bookDTO.getAuthor());
        assertEquals(book.getAuthor().getEmail(), bookDTO.getAuthor().getEmail());

        assertEquals(book.getChapters().size(), bookDTO.getChapters().size());
        assertEquals("Chapter 2", bookDTO.getChapters().get(1).getTitle());
    }


}