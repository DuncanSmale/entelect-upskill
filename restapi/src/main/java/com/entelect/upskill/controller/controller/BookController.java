package com.entelect.upskill.controller.controller;


import com.entelect.entities.Book;
import com.entelect.upskill.controller.model.BookEntity;
import com.entelect.upskill.controller.repository.BookRepository;
import com.entelect.upskill.controller.mapper.BookMapper;
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
@RequestMapping("book")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookRepository.findAll().stream().map(BookMapper.INSTANCE::toBookDTO).collect(Collectors.toList()));
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Integer bookId) {
        Optional<BookEntity> foundEntity = bookRepository.findById(bookId);
        if (foundEntity.isPresent()){
            Book book = BookMapper.INSTANCE.toBookDTO(foundEntity.get());
            return  ResponseEntity.ok(book);
        }else{
            return  ResponseEntity.badRequest().build();
        }

    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book bookToCreate) {
        bookToCreate.setBookId(null);
        BookEntity bookEntity = BookMapper.INSTANCE.toBookEntity(bookToCreate);
        BookEntity savedEntity = bookRepository.save(bookEntity);
        return new ResponseEntity<>(BookMapper.INSTANCE.toBookDTO(savedEntity), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Integer bookId, @RequestBody Book bookToSave) {
        bookToSave.setBookId(bookId);
        BookEntity bookEntity = BookMapper.INSTANCE.toBookEntity(bookToSave);
        BookEntity savedEntity = bookRepository.save(bookEntity);
        return new ResponseEntity<>(BookMapper.INSTANCE.toBookDTO(savedEntity),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable("id") Integer authorId) {
        bookRepository.deleteById(authorId);
    }
}
