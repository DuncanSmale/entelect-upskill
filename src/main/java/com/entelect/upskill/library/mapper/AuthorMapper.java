package com.entelect.upskill.library.mapper;

import com.entelect.upskill.library.dtos.AuthorDTO;
import com.entelect.upskill.library.model.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);
    AuthorDTO toAuthorDTO(AuthorEntity authorEntity);

    AuthorEntity toAuthorEntity(AuthorDTO authorDTO);
}
