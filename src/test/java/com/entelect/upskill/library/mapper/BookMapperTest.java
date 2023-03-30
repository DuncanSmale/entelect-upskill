package com.entelect.upskill.library.mapper;

import com.entelect.upskill.library.dtos.AuthorDTO;
import com.entelect.upskill.library.dtos.BookDTO;
import com.entelect.upskill.library.model.AuthorEntity;
import com.entelect.upskill.library.model.BookEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookMapperTest {

    @Test
    @DisplayName("Given a book entity, " +
            "When I call the to dto method, " +
            "The entity should be mapped correctly")
    void toBookDTO() {
        // Given
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle("Return of the King");
        bookEntity.setAuthor(createStubAuthorEntity());

        // When
        BookDTO convertedBook = BookMapper.INSTANCE.toBookDTO(bookEntity);

        // Then
        assertEquals("Return of the King", convertedBook.getTitle());
        assertEquals("Peter", convertedBook.getAuthor().getFirstName());
    }

    @Test
    @DisplayName("Given a book dto, " +
            "When I call the to entity method, " +
            "The dto should be mapped correctly")
    void toBookEntity() {
        // Given
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("Return of the King");
        bookDTO.setAuthor(createStubAuthorDTO());

        // When
        BookEntity convertedBook = BookMapper.INSTANCE.toBookEntity(bookDTO);

        // Then
        assertEquals("Return of the King", convertedBook.getTitle());
        assertEquals("Peter", convertedBook.getAuthor().getFirstName());
    }

    private AuthorEntity createStubAuthorEntity() {
        return new AuthorEntity(0,
                "Peter",
                "Ryan",
                "South Africa",
                "p@r.com",
                false);
    }

    private AuthorDTO createStubAuthorDTO() {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setAuthorId(0);
        authorDTO.setFirstName("Peter");
        authorDTO.setLastName("Ryan");
        authorDTO.setEmailAddress("p@r.com");
        authorDTO.setCountryOfResidence("South Africa");
        return authorDTO;
    }
}