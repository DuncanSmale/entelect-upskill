package com.entelect.upskill.bookinformation;

import com.entelect.upskill.model.AuthorEntity;
import com.entelect.upskill.model.BookEntity;
import com.entelect.upskill.repository.AuthorRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookInformationGatewayImp implements BookInformationGateway {

    private final AuthorRepository authorRepository;

    public BookInformationGatewayImp(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Response getBookInformation() {
        List<AuthorEntity> allAuthors = authorRepository.findAll();
        List<String> bookInformationList = new ArrayList<>();
        for (AuthorEntity author : allAuthors) {
            for (BookEntity book : author.getBooks()) {
                String bookInformation = String.format("%s %s, %s published by publisher %s.",
                        author.getFirstName(),
                        author.getLastName(),
                        book.getTitle(),
                        book.getPublisher());
                bookInformationList.add(bookInformation);
            }

        }

        BookInformationGateway.Response response = new BookInformationGateway.Response();
        response.setBookInformationList(bookInformationList);
        return response;
    }
}
