package com.entelect.upskill.bookinformation;

import com.entelect.upskill.mapper.AuthorMapper;
import com.entelect.upskill.model.AuthorEntity;
import com.entelect.upskill.repository.AuthorRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetAuthorsGatewayImp implements GetAuthorsGateway {

    private final AuthorRepository authorRepository;

    public GetAuthorsGatewayImp(AuthorRepository authorRepository)
    {
        this.authorRepository = authorRepository;
    }

    @Override
    public Response getAuthors() {
        List<AuthorEntity> allAuthors = authorRepository.findAll();
        GetAuthorsGateway.Response response = new GetAuthorsGateway.Response();
        response.setAuthorList(allAuthors
                .stream()
                .map(AuthorMapper.INSTANCE::toAuthorDTO)
                .collect(Collectors.toList()));
        return response;
    }
}
