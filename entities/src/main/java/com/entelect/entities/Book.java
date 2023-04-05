package com.entelect.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Book {
    private Integer bookId;
    private Integer authorId;

    private String title;

    private LocalDate publishedDate;

    private String publisher;

    private String ISBN;


}
