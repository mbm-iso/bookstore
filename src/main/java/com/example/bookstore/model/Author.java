package com.example.bookstore.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    private String firstName;
    private String lastName;
    private String email;
    private String biography;
    private LocalDate birthDate;
}
