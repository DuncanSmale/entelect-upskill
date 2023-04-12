package com.entelect.upskill.controller.bookinformation;

import com.entelect.upskill.bookinformation.GetBookInformation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("book-information")
@RequiredArgsConstructor
public class BookInformationController {

    private final GetBookInformation getBookInformation;

    @GetMapping
    public ResponseEntity<Response> getBookInformation() {
        GetBookInformation.Response bookInformationStringList = getBookInformation.getBookInformation();
        Response bookInformationResponse = new Response();
        bookInformationResponse.setBookInformationList(bookInformationStringList.getBookInformationList());
        return ResponseEntity.ok(bookInformationResponse);
    }

    @Setter
    @Getter
    public class Response {
        List<String> bookInformationList;
    }
}
