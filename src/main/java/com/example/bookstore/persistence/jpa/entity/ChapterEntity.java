package com.example.bookstore.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ChapterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    @Column(name = "number", nullable = false)
    private int number;
    @Column(name = "title", nullable = false, length = 100)
    private String title;
    @Column(name = "summary", nullable = false, columnDefinition = "TEXT")
    private String summary;
}
