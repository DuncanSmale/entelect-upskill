package com.entelect.upskill.controller.concatenatebooktitle;

import com.entelect.upskill.concatenatebooktitles.BookTitlesStringByAuthorGatewayImpl;
import com.entelect.upskill.usecases.concatenatebooktitles.BookTitlesStringByAuthorGateway;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book-titles")
@RequiredArgsConstructor
public class BookTitlesController {

    private final BookTitlesStringByAuthorGatewayImpl concatenateBookTitlesGatewayImpl;

    @PostMapping
    public ConcatenateBookTitlesResponse getBookTitlesByAuthor(@RequestBody ConcatenateBookTitlesRequest request) {
        BookTitlesStringByAuthorGateway.Response concatenationResponse = concatenateBookTitlesGatewayImpl
                .getBookTitlesByAuthor(ConcatenateTitlesMapper.INSTANCE.toRequest(request));
        return ConcatenateTitlesMapper.INSTANCE.toResponse(concatenationResponse);
    }

    @Getter
    @Setter
    public static class ConcatenateBookTitlesRequest {
        private Integer authorId;
    }

    @Getter
    @Setter
    public static class ConcatenateBookTitlesResponse {
        private String bookTitlesString;
    }

}
