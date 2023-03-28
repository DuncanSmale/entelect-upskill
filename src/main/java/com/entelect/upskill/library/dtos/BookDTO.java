package com.entelect.upskill.library.dtos;

import com.entelect.upskill.library.model.AuthorEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
public class BookDTO {
    private Integer bookId;
    private AuthorDTO author;
    private String title;
    private LocalDate publishedDate;
    private String publisher;
    private String ISBN;
    private boolean deleted;
}
