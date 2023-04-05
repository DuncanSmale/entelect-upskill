package com.entelect.upskill.controller.mapper;



import com.entelect.entities.Author;
import com.entelect.upskill.controller.model.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);
    Author toAuthorDTO(AuthorEntity authorEntity);

    AuthorEntity toAuthorEntity(Author authorDTO);
}
