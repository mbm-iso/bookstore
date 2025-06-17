package com.example.bookstore.mapper;

import com.example.bookstore.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorDTOMapper {
    String toString(Author author);
}