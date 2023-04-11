package com.entelect.upskill.usecases.concatenatebooktitles;

import lombok.Getter;
import lombok.Setter;

public interface BookTitlesStringByAuthorGateway {

    Response getBookTitlesByAuthor(Request request);

    @Getter
    @Setter
    class Response {
        private String bookTitlesString;
    }

    @Getter
    @Setter
    class Request {
        private Integer authorId;
    }
}
