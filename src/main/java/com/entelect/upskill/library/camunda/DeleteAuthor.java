package com.entelect.upskill.library.camunda;

import com.entelect.upskill.library.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Named
public class DeleteAuthor implements JavaDelegate {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional
    public void execute(DelegateExecution execution) throws Exception {
        Integer authorId = (Integer) execution.getVariable("authorId");
        try {
            authorRepository.deleteById(authorId);
            execution.setVariable("path", "LESS THAN OR EQUAL TO");
        } catch (Exception e) {
            String errorMessage = "Could not delete author with ID: " + authorId;
            String errorLocation = "DeleteAuthor";
            execution.setVariable("errorLocation", errorLocation);
            execution.setVariable("errorMessage", errorMessage);
            throw new BpmnError(errorLocation);
        }
    }
}