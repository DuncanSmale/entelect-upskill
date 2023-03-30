package com.entelect.upskill.library.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookDTO {
    private Integer bookId;
    private Integer authorId;
    private String title;
    private LocalDate publishedDate;
    private String publisher;
    private String ISBN;
    private boolean deleted;
}
