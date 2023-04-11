package com.entelect.upskill.usecases.concatenatebooktitles;

import com.entelect.entities.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public interface GetBooksByAuthorIdGateway {

    Response getBookTitlesByAuthor(Integer authorId);

    @Getter
    @Setter
    class Response {
        private List<Book> booksFromAuthor;
    }
}
