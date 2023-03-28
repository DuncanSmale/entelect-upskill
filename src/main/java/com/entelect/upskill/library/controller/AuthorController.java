package com.entelect.upskill.library.controller;

import com.entelect.upskill.library.model.AuthorEntity;
import com.entelect.upskill.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("author")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @GetMapping
    public ResponseEntity<List<AuthorEntity>> getAllAuthors() {
        return ResponseEntity.ok(authorRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<AuthorEntity> getAuthorById(@PathVariable("id") Integer authorId) {
        Optional<AuthorEntity> foundEntity = authorRepository.findById(authorId);
        return foundEntity.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping
    public ResponseEntity<AuthorEntity> createAuthor(@RequestBody AuthorEntity authorToCreate) {
        authorToCreate.setAuthorId(null);
        AuthorEntity savedEntity = authorRepository.save(authorToCreate);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<AuthorEntity> updateAuthor(@PathVariable("id") Integer authorId, @RequestBody AuthorEntity authorToSave) {
        authorToSave.setAuthorId(authorId);
        AuthorEntity savedEntity = authorRepository.save(authorToSave);
        return new ResponseEntity<>(savedEntity, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public void deleteAuthor(@PathVariable("id") Integer authorId) {
        authorRepository.deleteById(authorId);
    }
}
