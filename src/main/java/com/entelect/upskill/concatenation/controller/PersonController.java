package com.entelect.upskill.concatenation.controller;

import com.entelect.upskill.concatenation.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("concatenate")
public class PersonController {

    @PostMapping
    public ResponseEntity<String> getConcatenatePerson(@RequestBody Person person){
        String concatenatedString = "Hello " + person.getName() + " you are " + person.getAge() +" years old";
        return ResponseEntity.ok( concatenatedString);
    }
}