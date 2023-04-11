package com.entelect.upskill.bookinformation;

import com.entelect.entities.Author;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public interface GetAuthorsGateway {

    Response getAuthors();

    @Setter
    @Getter
    class Response {
        List<Author> authorList;
    }


}
