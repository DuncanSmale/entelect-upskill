package com.entelect.upskill.library.controller;

import com.entelect.upskill.library.model.AuthorEntity;
import com.entelect.upskill.library.model.BookEntity;
import com.entelect.upskill.library.repository.AuthorRepository;
import com.entelect.upskill.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("book")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @GetMapping
    public ResponseEntity<List<BookEntity>> getAllBooks() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<BookEntity> getBookById(@PathVariable("id") Integer bookId) {
        Optional<BookEntity> foundEntity = bookRepository.findById(bookId);
        return foundEntity.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping
    public ResponseEntity<BookEntity> createBook(@RequestBody BookEntity bookToCreate) {
        bookToCreate.setBookId(null);
        BookEntity savedEntity = bookRepository.save(bookToCreate);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<BookEntity> updateBook(@PathVariable("id") Integer bookId, @RequestBody BookEntity bookToSave) {
        bookToSave.setBookId(bookId);
        BookEntity savedEntity = bookRepository.save(bookToSave);
        return new ResponseEntity<>(savedEntity, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable("id") Integer authorId) {
        bookRepository.deleteById(authorId);
    }
}
