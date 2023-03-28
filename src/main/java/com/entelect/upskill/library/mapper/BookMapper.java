package com.entelect.upskill.library.mapper;

import com.entelect.upskill.library.dtos.BookDTO;
import com.entelect.upskill.library.model.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    BookDTO toBookDTO(BookEntity bookEntity);
    BookEntity toBookEntity(BookDTO bookDTO);
}
