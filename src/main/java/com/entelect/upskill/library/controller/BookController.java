package com.entelect.upskill.library.controller;

import com.entelect.upskill.library.dtos.AuthorDTO;
import com.entelect.upskill.library.dtos.BookDTO;
import com.entelect.upskill.library.mapper.AuthorMapper;
import com.entelect.upskill.library.mapper.BookMapper;
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
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookRepository.findAll().stream().map(BookMapper.INSTANCE::toBookDTO).collect(Collectors.toList()));
    }

    @GetMapping("{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("id") Integer bookId) {
        Optional<BookEntity> foundEntity = bookRepository.findById(bookId);
        if (foundEntity.isPresent()){
            BookDTO bookDTO = BookMapper.INSTANCE.toBookDTO(foundEntity.get());
            return  ResponseEntity.ok(bookDTO);
        }else{
            return  ResponseEntity.badRequest().build();
        }

    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookToCreate) {
        bookToCreate.setBookId(null);
        BookEntity bookEntity = BookMapper.INSTANCE.toBookEntity(bookToCreate);
        BookEntity savedEntity = bookRepository.save(bookEntity);
        return new ResponseEntity<>(BookMapper.INSTANCE.toBookDTO(savedEntity),HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable("id") Integer bookId, @RequestBody BookDTO bookToSave) {
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
