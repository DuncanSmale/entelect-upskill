package com.entelect.upskill.controller.concatenatebooktitle;

import com.entelect.upskill.usecases.concatenatebooktitles.BookTitleStringForAuthor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ConcatenateTitlesMapper {
    ConcatenateTitlesMapper INSTANCE = Mappers.getMapper(ConcatenateTitlesMapper.class);

    BookTitleStringForAuthor.Request toRequest(BookTitlesController.ConcatenateBookTitlesRequest incomingRequest);

    BookTitlesController.ConcatenateBookTitlesResponse toResponse(BookTitleStringForAuthor.Response outGoingResponse);
}
