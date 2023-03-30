package com.entelect.upskill.library.controller;

import com.entelect.upskill.library.dtos.BookDTO;
import com.entelect.upskill.library.mapper.BookMapper;
import com.entelect.upskill.library.model.BookEntity;
import com.entelect.upskill.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.impl.persistence.entity.ProcessInstanceWithVariablesImpl;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("book")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final RuntimeService runtimeService;

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

    @GetMapping("count-by-author")
    public  ResponseEntity<List<Long>> getBookCountByAuthor(){
//       List<Long> bookCountsPerAuthor =  bookRepository.countBookEntitiesByAuthorId();
       return ResponseEntity.ok(List.of());
    }

    @GetMapping("count-by-author/{id}")
    public void getBookCountByAuthor(@PathVariable("id") Integer authorId){
        Map<String, Object> processVariables = new HashMap<>();

        processVariables.put("authorId", authorId);

        ProcessInstanceWithVariablesImpl process = (ProcessInstanceWithVariablesImpl)
                runtimeService.startProcessInstanceByKey(
                        "request-author-book-count",
                        processVariables
                );
    }
}
