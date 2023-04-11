package com.entelect.upskill.mapper;



import com.entelect.entities.Author;
import com.entelect.upskill.model.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);
    Author toAuthorDTO(AuthorEntity authorEntity);

    AuthorEntity toAuthorEntity(Author authorDTO);
}
