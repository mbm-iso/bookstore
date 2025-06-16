package com.example.bookstore.persistence.mapper;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.Chapter;
import com.example.bookstore.persistence.jpa.entity.BookEntity;
import com.example.bookstore.persistence.jpa.entity.ChapterEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookEntityMapper {

    Book bookEntityToBook(BookEntity bookEntity);

    @Mapping(target = "chapters", ignore = true) // we will handle chapters manually
    BookEntity bookToBookEntity(Book book);

    ChapterEntity chapterToChapterEntity(Chapter chapter);

    @AfterMapping
    default void afterMapping(@MappingTarget BookEntity bookEntity, Book book) {
        if (book.getChapters() != null) {
            for (Chapter chapter : book.getChapters()) {
                ChapterEntity chapterEntity = chapterToChapterEntity(chapter);
                bookEntity.addChapter(chapterEntity);
            }
        }
    }

    BookEntity updateBookEntity(@MappingTarget BookEntity bookEntity, Book book);
}
