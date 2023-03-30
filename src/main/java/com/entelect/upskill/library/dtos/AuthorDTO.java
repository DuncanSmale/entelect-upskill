package com.entelect.upskill.library.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthorDTO {

    private Integer authorId;
    private String firstName;
    private String lastName;
    private String countryOfResidence;
    private String emailAddress;
    private boolean deleted;
    private List<BookDTO> books;
}
