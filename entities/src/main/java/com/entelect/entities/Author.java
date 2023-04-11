package com.entelect.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Author {
    private Integer authorId;
    private String firstName;
    private String lastName;
    private String countryOfResidence;
    private String emailAddress;
    private List<Book> books;
}
