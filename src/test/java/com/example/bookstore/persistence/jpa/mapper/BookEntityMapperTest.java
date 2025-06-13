package com.example.bookstore.persistence.jpa.mapper;

import com.example.bookstore.model.Author;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Chapter;
import com.example.bookstore.persistence.jpa.entity.AuthorEntity;
import com.example.bookstore.persistence.jpa.entity.BookEntity;
import com.example.bookstore.persistence.jpa.entity.ChapterEntity;
import com.example.bookstore.persistence.mapper.BookEntityMapper;
import com.example.bookstore.persistence.mapper.BookEntityMapperImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookEntityMapperTest {

    BookEntityMapper mapper = new BookEntityMapperImpl();

    @Test
    void bookEntityToBook() {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setFirstName("Martin");
        authorEntity.setLastName("Fowler");
        authorEntity.setEmail("mfowler@example.com");
        authorEntity.setBiography("Author and software architect.");
        authorEntity.setBirthDate(LocalDate.of(1963, 12, 18));

        ChapterEntity chapter1 = new ChapterEntity();
        chapter1.setNumber(1);
        chapter1.setTitle("Intro");
        chapter1.setSummary("Summary 1");

        ChapterEntity chapter2 = new ChapterEntity();
        chapter2.setNumber(2);
        chapter2.setTitle("Design");
        chapter2.setSummary("Summary 2");

        BookEntity bookEntity = new BookEntity();
        bookEntity.setIsbn("123-456");
        bookEntity.setTitle("Refactoring");
        bookEntity.setPublished(true);
        bookEntity.setAuthor(authorEntity);
        bookEntity.setChapters(List.of(chapter1, chapter2));


        Book book = mapper.bookEntityToBook(bookEntity);

        assertNotNull(book);
        assertEquals("123-456", book.getIsbn());
        assertEquals("Refactoring", book.getTitle());
        assertTrue(book.isPublished());
        assertEquals("Martin", book.getAuthor().getFirstName());
        assertEquals(2, book.getChapters().size());
        assertEquals("Intro", book.getChapters().getFirst().getTitle());
    }

    @Test
    void bookToBookEntity() {
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

        BookEntity entity = mapper.bookToBookEntity(book);

        assertNotNull(entity);
        assertEquals("123-456", entity.getIsbn());
        assertEquals("Refactoring", entity.getTitle());
        assertTrue(entity.isPublished());
        assertEquals("Martin", entity.getAuthor().getFirstName());
        assertEquals(2, entity.getChapters().size());
        assertEquals("Design", entity.getChapters().get(1).getTitle());
    }

}