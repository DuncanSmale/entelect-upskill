package com.entelect.upskill.controller.mapper;


import com.entelect.entities.Book;
import com.entelect.upskill.controller.model.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;



@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    Book toBookDTO(BookEntity bookEntity);
    BookEntity toBookEntity(Book bookDTO);
}
