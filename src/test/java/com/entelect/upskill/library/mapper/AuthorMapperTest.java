package com.entelect.upskill.library.mapper;

import com.entelect.upskill.library.dtos.AuthorDTO;
import com.entelect.upskill.library.model.AuthorEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorMapperTest {

    @Test
    @DisplayName("Given an entity, " +
            "When the to DTO method is called, " +
            "The entity should be mapped correctly")
    void toAuthorDTO() {
        // Given
        AuthorEntity author = new AuthorEntity();
        author.setFirstName("Peter");

        // When
        AuthorDTO convertedAuthor = AuthorMapper.INSTANCE.toAuthorDTO(author);

        // Then
        assertEquals("Peter", convertedAuthor.getFirstName());
    }

    @Test
    @DisplayName("Given a DTO, " +
            "When the to entity method is called, " +
            "The DTO should be mapped correctly")
    void toAuthorEntity() {
        // Given
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setFirstName("Ryan");

        // When
        AuthorEntity convertedAuthor = AuthorMapper.INSTANCE.toAuthorEntity(authorDTO);

        // Then
        assertEquals("Ryan", convertedAuthor.getFirstName());
    }
}