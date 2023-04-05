package com.entelect.upskill.controller.controller;


import com.entelect.entities.Author;
import com.entelect.upskill.controller.mapper.AuthorMapper;
import com.entelect.upskill.controller.model.AuthorEntity;
import com.entelect.upskill.controller.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<Author>> getAllAuthors() {
        return ResponseEntity.ok(authorRepository.findAll().stream().map(AuthorMapper.INSTANCE::toAuthorDTO).collect(Collectors.toList()));
    }

    @GetMapping("{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable("id") Integer authorId) {
        Optional<AuthorEntity> foundEntity = authorRepository.findById(authorId);
        if (foundEntity.isPresent()){
            Author author = AuthorMapper.INSTANCE.toAuthorDTO(foundEntity.get());
            return  ResponseEntity.ok(author);
        }else{
            return  ResponseEntity.badRequest().build();
        }

    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author authorToCreate) {
        authorToCreate.setAuthorId(null);
        AuthorEntity authorEntity = AuthorMapper.INSTANCE.toAuthorEntity(authorToCreate);
        AuthorEntity savedEntity = authorRepository.save(authorEntity);
        return new ResponseEntity<>(AuthorMapper.INSTANCE.toAuthorDTO(savedEntity),HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable("id") Integer authorId, @RequestBody Author authorToSave) {
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
