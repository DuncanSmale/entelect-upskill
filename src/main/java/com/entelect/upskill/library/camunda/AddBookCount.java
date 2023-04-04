package com.entelect.upskill.library.camunda;

import com.entelect.upskill.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@RequiredArgsConstructor
@Named
public class AddBookCount implements JavaDelegate {
    private final BookRepository bookRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Integer authorId = (Integer) execution.getVariable("authorId");
        try {
            Long count = bookRepository.countBookEntitiesByAuthorId(authorId);
            execution.setVariable("count", count);
        } catch (Exception e) {
            String errorMessage = "Could not find author with ID: " + authorId;
            String errorLocation = "AddBookCount";
            execution.setVariable("errorLocation", errorLocation);
            execution.setVariable("errorMessage", errorMessage);
            throw new BpmnError(errorLocation);
        }
    }
}
