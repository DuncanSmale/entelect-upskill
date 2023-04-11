package com.entelect.upskill.concatenatebooktitles;

import com.entelect.upskill.model.BookEntity;
import com.entelect.upskill.repository.BookRepository;
import com.entelect.upskill.usecases.concatenatebooktitles.BookTitlesStringByAuthor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class BookTitlesStringByAuthorImpl implements BookTitlesStringByAuthor {

    private final BookRepository bookRepository;

    @Override
    public BookTitlesStringByAuthor.Response getBookTitlesByAuthor(BookTitlesStringByAuthor.Request request) {
        List<BookEntity> bookList = bookRepository.findBookEntitiesByAuthorId(request.getAuthorId());
        String concatenatedTitles = bookList.stream().map(BookEntity::getTitle).collect(Collectors.joining(", "));
        BookTitlesStringByAuthor.Response response = new BookTitlesStringByAuthor.Response();
        response.setBookTitlesString(concatenatedTitles);
        return response;
    }
}
