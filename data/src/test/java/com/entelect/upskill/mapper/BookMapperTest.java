package com.entelect.upskill.mapper;

import com.entelect.entities.Book;
import com.entelect.upskill.model.BookEntity;
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
        Book convertedBook = BookMapper.INSTANCE.toBookDTO(bookEntity);

        // Then
        assertEquals("Return of the King", convertedBook.getTitle());

    }

    @Test
    @DisplayName("Given a book dto, " +
            "When I call the to entity method, " +
            "The dto should be mapped correctly")
    void toBookEntity() {
        // Given
        Book bookDTO = new Book();
        bookDTO.setTitle("Return of the King");


        // When
        BookEntity convertedBook = BookMapper.INSTANCE.toBookEntity(bookDTO);

        // Then
        assertEquals("Return of the King", convertedBook.getTitle());
    }



}