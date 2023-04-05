package com.entelect.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Author {
    private Integer authorId;
    private String firstName;
    private String lastName;
    private String countryOfResidence;
    private String emailAddress;

}
