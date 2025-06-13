package com.example.bookstore.mapper;

import com.example.bookstore.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorDTOMapper {
    Author fromString(String name);
    String toString(Author author);
}