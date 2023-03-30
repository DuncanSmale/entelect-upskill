package com.entelect.upskill.library.mapper;

import com.entelect.upskill.library.dtos.BookDTO;
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

        // When
        BookDTO convertedBook = BookMapper.INSTANCE.toBookDTO(bookEntity);

        // Then
        assertEquals("Return of the King", convertedBook.getTitle());
    }

    @Test
    @DisplayName("Given a book dto, " +
            "When I call the to entity method, " +
            "The dto should be mapped correctly")
    void toBookEntity() {
        // Given
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("Return of the King");

        // When
        BookEntity convertedBook = BookMapper.INSTANCE.toBookEntity(bookDTO);

        // Then
        assertEquals("Return of the King", convertedBook.getTitle());
    }
}