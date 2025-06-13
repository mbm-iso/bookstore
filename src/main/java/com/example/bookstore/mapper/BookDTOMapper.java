package com.example.bookstore.mapper;

import com.example.bookstore.generated.dto.BookDTO;
import com.example.bookstore.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = AuthorDTOMapper.class)
public interface BookDTOMapper {

    Book bookDTOToBook(BookDTO bookDTO);
    BookDTO bookToBookDTO(Book book);

}
