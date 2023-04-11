package com.entelect.upskill.usecases.concatenatebooktitles;

import com.entelect.entities.Book;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookTitleStringForAuthor {
    private final GetBooksByAuthorIdGateway getBooksByAuthorIdGateway;

    public Response getBookTitleString(Request incomingRequest) {
        GetBooksByAuthorIdGateway.Response bookList = getBooksByAuthorIdGateway.getBookTitlesByAuthor(incomingRequest.authorId);
        String concatenatedTitles = bookList.getBooksFromAuthor()
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.joining(", "));
        BookTitleStringForAuthor.Response response = new BookTitleStringForAuthor.Response();
        response.setBookTitleString(concatenatedTitles);
        return response;
    }

    @Getter
    @Setter
    public static class Request {
        private Integer authorId;
    }

    @Getter
    @Setter
    public static class Response {
        private String bookTitleString;
    }
}
