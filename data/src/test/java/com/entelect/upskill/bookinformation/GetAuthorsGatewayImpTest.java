package com.entelect.upskill.bookinformation;


import com.entelect.upskill.model.AuthorEntity;
import com.entelect.upskill.model.BookEntity;
import com.entelect.upskill.repository.AuthorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAuthorsGatewayImpTest {
    @InjectMocks
    GetAuthorsGatewayImp cut;
    @Mock
    AuthorRepository authorRepository;


    @Test
    @DisplayName("Given a book information request, " +
            "when the getBookInformation is called, " +
            "then we expect the returned display list to be correct")
    public void getBookInformationTest() {
        // Given
        when(authorRepository.findAll()).thenReturn(stubAuthorList());

        // When
        GetAuthorsGateway.Response bookInformationResponse = cut.getAuthors();

        // Then

    }



    private List<AuthorEntity> stubAuthorList() {
        List<AuthorEntity> authors = new ArrayList<>();
        List<BookEntity> firstAuthorbookList = new ArrayList<>();
        List<BookEntity> secondAuthorbookList = new ArrayList<>();
        secondAuthorbookList.add(new BookEntity(
                1,
                2,
                "Tapped for Sorrow",
                LocalDate.of(2021,12,26),
                "Jonathan Ball",
                "0-2487-9445-0"));

        firstAuthorbookList.add(new BookEntity(
                        1,
                        1,
                        "Happy Peter and the Wizard of Escabar",
                        LocalDate.of(2012,04,22),
                        "Penguin Books",
                        "0-2487-9445-0"));
        firstAuthorbookList.add (new BookEntity(
                2,
                1,
                "The Face of the Eight",
                LocalDate.of(2005,04,28),
                "Exclusive Books",
                "0-8665-9950-9"));
        AuthorEntity firstAuthor = new AuthorEntity(
                1,
                "Braeden",
                "Zoo",
                "South Africa",
                "bradenZoo@gmail",
                firstAuthorbookList);
        AuthorEntity secondAuthor = new AuthorEntity(
                2,
                "Steven",
                "Strange",
                "USA",
                "stevenStrange@gmail",
                secondAuthorbookList);
       authors.add(firstAuthor);
       authors.add(secondAuthor);

       return authors;
    }

}