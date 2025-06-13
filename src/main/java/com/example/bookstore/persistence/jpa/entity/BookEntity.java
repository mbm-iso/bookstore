package com.example.bookstore.persistence.jpa.entity;

import com.example.bookstore.model.Author;
import com.example.bookstore.model.Chapter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString(exclude = "chapters")
@Data
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "title", updatable = true, nullable = false)
    private String title;

    @Column(name = "published")
    private boolean published;

    @Column(name = "isbn", updatable = false, nullable = false)
    private String isbn;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id", name = "author_id")
    private AuthorEntity author;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ChapterEntity> chapters;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdAt;
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        OffsetDateTime now = OffsetDateTime.now();
        if (createdAt == null) {
            createdAt = now;
        }
        updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = OffsetDateTime.now();
    }

    public void addChapter(ChapterEntity chapter) {
        if(chapters == null) {
            chapters = new ArrayList<>();
        }
        chapters.add(chapter);
        chapter.setBook(this);
    }

    public void removeChapter(ChapterEntity chapter) {
        if(chapters == null) {throw new RuntimeException("chapters is null");}
        chapters.remove(chapter);
        chapter.setBook(null);
    }
}
