package com.entelect.upskill.concatenatebooktitles;

import com.entelect.upskill.model.BookEntity;
import com.entelect.upskill.repository.BookRepository;
import com.entelect.upskill.usecases.concatenatebooktitles.BookTitlesStringByAuthorGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class BookTitlesStringByAuthorGatewayImpl implements BookTitlesStringByAuthorGateway {

    private final BookRepository bookRepository;

    @Override
    public BookTitlesStringByAuthorGateway.Response getBookTitlesByAuthor(BookTitlesStringByAuthorGateway.Request request) {
        List<BookEntity> bookList = bookRepository.findBookEntitiesByAuthorId(request.getAuthorId());
        String concatenatedTitles = bookList.stream().map(BookEntity::getTitle).collect(Collectors.joining(", "));
        BookTitlesStringByAuthorGateway.Response response = new BookTitlesStringByAuthorGateway.Response();
        response.setBookTitlesString(concatenatedTitles);
        return response;
    }
}
