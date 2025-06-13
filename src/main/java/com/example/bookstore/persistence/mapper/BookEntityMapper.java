package com.example.bookstore.persistence.mapper;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.Chapter;
import com.example.bookstore.persistence.jpa.entity.BookEntity;
import com.example.bookstore.persistence.jpa.entity.ChapterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BookEntityMapper {

    Book bookEntityToBook(BookEntity bookEntity);
    BookEntity bookToBookEntity(Book book);

    BookEntity updateBookEntity(@MappingTarget BookEntity bookEntity, Book book);
}
