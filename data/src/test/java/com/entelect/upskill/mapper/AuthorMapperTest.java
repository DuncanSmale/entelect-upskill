package com.entelect.upskill.mapper;

import com.entelect.entities.Author;
import com.entelect.upskill.model.AuthorEntity;
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
        Author convertedAuthor = AuthorMapper.INSTANCE.toAuthorDTO(author);

        // Then
        assertEquals("Peter", convertedAuthor.getFirstName());
    }

    @Test
    @DisplayName("Given a DTO, " +
            "When the to entity method is called, " +
            "The DTO should be mapped correctly")
    void toAuthorEntity() {
        // Given
        Author author = new Author();
        author.setFirstName("Ryan");

        // When
        AuthorEntity convertedAuthor = AuthorMapper.INSTANCE.toAuthorEntity(author);

        // Then
        assertEquals("Ryan", convertedAuthor.getFirstName());
    }
}