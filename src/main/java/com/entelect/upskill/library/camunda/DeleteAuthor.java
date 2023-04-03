package com.entelect.upskill.library.camunda;

import com.entelect.upskill.library.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@RequiredArgsConstructor
@Named
public class DeleteAuthor implements JavaDelegate {

    private final AuthorRepository authorRepository;

    @Override
    public void execute(DelegateExecution execution) {
        Integer authorId = (Integer) execution.getVariable("authorId");

        authorRepository.deleteById(authorId);

        execution.setVariable("path", "LESS THAN OR EQUAL TO");
    }
}
