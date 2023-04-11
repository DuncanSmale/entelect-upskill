package com.entelect.upskill.concatenatebooktitles;

import com.entelect.upskill.mapper.BookMapper;
import com.entelect.upskill.model.BookEntity;
import com.entelect.upskill.repository.BookRepository;
import com.entelect.upskill.usecases.concatenatebooktitles.GetBooksByAuthorIdGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class GetBooksByAuthorIdGatewayImpl implements GetBooksByAuthorIdGateway {

    private final BookRepository bookRepository;

    @Override
    public Response getBookTitlesByAuthor(Integer authorId) {
        List<BookEntity> bookList = bookRepository.findBookEntitiesByAuthorId(authorId);
        Response outGoingResponse = new Response();
        outGoingResponse.setBooksFromAuthor(bookList
                .stream()
                .map(BookMapper.INSTANCE::toBookDTO)
                .collect(Collectors.toList()));
        return outGoingResponse;
    }
}
