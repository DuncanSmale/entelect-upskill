package com.entelect.upskill.library.controller;

import com.entelect.upskill.library.dtos.AuthorDTO;
import com.entelect.upskill.library.mapper.AuthorMapper;
import com.entelect.upskill.library.model.AuthorEntity;
import com.entelect.upskill.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("author")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        return ResponseEntity.ok(authorRepository.findAll().stream().map(AuthorMapper.INSTANCE::toAuthorDTO).collect(Collectors.toList()));
    }

    @GetMapping("{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable("id") Integer authorId) {
        Optional<AuthorEntity> foundEntity = authorRepository.findById(authorId);
        if (foundEntity.isPresent()){
            AuthorDTO authorDTO = AuthorMapper.INSTANCE.toAuthorDTO(foundEntity.get());
            return  ResponseEntity.ok(authorDTO);
        }else{
            return  ResponseEntity.badRequest().build();
        }

    }

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorToCreate) {
        authorToCreate.setAuthorId(null);
        AuthorEntity authorEntity = AuthorMapper.INSTANCE.toAuthorEntity(authorToCreate);
        AuthorEntity savedEntity = authorRepository.save(authorEntity);
        return new ResponseEntity<>(AuthorMapper.INSTANCE.toAuthorDTO(savedEntity),HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable("id") Integer authorId, @RequestBody AuthorDTO authorToSave) {
        authorToSave.setAuthorId(authorId);
        AuthorEntity authorEntity = AuthorMapper.INSTANCE.toAuthorEntity(authorToSave);
        AuthorEntity savedEntity = authorRepository.save(authorEntity);
        return new ResponseEntity<>(AuthorMapper.INSTANCE.toAuthorDTO(savedEntity), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public void deleteAuthor(@PathVariable("id") Integer authorId) {
        authorRepository.deleteById(authorId);
    }
}
