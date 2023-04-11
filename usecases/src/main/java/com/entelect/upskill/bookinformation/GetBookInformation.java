package com.entelect.upskill.bookinformation;

import com.entelect.entities.Author;
import com.entelect.entities.Book;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetBookInformation {
    private final GetAuthorsGateway getAuthorsGateway;

    public Response getBookInformation() {
        List<Author> authorList = getAuthorsGateway.getAuthors().authorList;
        List<String> bookInformationList = new ArrayList<>();
        for (Author author : authorList) {
            for (Book book : author.getBooks()) {
                String bookInformation = String.format("%s %s, %s published by publisher %s.",
                        author.getFirstName(),
                        author.getLastName(),
                        book.getTitle(),
                        book.getPublisher());
                bookInformationList.add(bookInformation);
            }

        }

        Response response = new Response();
        response.setBookInformationList(bookInformationList);
        return response;

    }

    @Setter
    @Getter
    public class Response {
        List<String> bookInformationList;
    }
}
