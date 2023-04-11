package com.entelect.upskill.controller.concatenatebooktitle;

import com.entelect.upskill.usecases.concatenatebooktitles.BookTitlesStringByAuthorGateway;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ConcatenateTitlesMapper {
    ConcatenateTitlesMapper INSTANCE = Mappers.getMapper(ConcatenateTitlesMapper.class);

    BookTitlesStringByAuthorGateway.Request toRequest(BookTitlesController.ConcatenateBookTitlesRequest incomingRequest);

    BookTitlesController.ConcatenateBookTitlesResponse toResponse(BookTitlesStringByAuthorGateway.Response outGoingResponse);
}
