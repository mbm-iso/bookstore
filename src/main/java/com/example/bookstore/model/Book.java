package com.example.bookstore.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Book {

    private String title;
    private boolean published;
    private String isbn;
    private Author author;
    private List<Chapter> chapters;
}
