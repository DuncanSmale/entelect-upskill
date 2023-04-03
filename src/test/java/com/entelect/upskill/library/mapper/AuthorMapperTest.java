package com.entelect.upskill.library.mapper;

import com.entelect.upskill.library.dtos.AuthorDTO;
import com.entelect.upskill.library.model.AuthorEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorMapperTest {

    @Test
    @DisplayName("Given an entity, " +
            "When the to DTO method is called, " +
            "The entity should be mapped correctly")
    void toAuthorDTO() {
        // Given
        AuthorEntity author = new AuthorEntity();
        author.setFirstName("Peter");
        author.setLastName("Ryan");
        author.setEmailAddress("p@r.com");
        author.setCountryOfResidence("South Africa");

        // When
        AuthorDTO convertedAuthor = AuthorMapper.INSTANCE.toAuthorDTO(author);

        // Then
        assertEquals("Peter", convertedAuthor.getFirstName());
        assertEquals("Ryan", convertedAuthor.getLastName());
        assertEquals("p@r.com", convertedAuthor.getEmailAddress());
        assertEquals("South Africa", convertedAuthor.getCountryOfResidence());
    }

    @Test
    @DisplayName("Given a DTO, " +
            "When the to entity method is called, " +
            "The DTO should be mapped correctly")
    void toAuthorEntity() {
        // Given
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setFirstName("Ryan");
        authorDTO.setLastName("Peter");
        authorDTO.setEmailAddress("r@p.com");
        authorDTO.setCountryOfResidence("UK");

        // When
        AuthorEntity convertedAuthor = AuthorMapper.INSTANCE.toAuthorEntity(authorDTO);

        // Then
        assertEquals("Ryan", convertedAuthor.getFirstName());
        assertEquals("Peter", convertedAuthor.getLastName());
        assertEquals("r@p.com", convertedAuthor.getEmailAddress());
        assertEquals("UK", convertedAuthor.getCountryOfResidence());
    }
}