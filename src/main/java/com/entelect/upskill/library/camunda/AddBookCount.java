package com.entelect.upskill.library.camunda;

import com.entelect.upskill.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@RequiredArgsConstructor
@Named
public class AddBookCount implements JavaDelegate {
    private final BookRepository bookRepository;

    @Override
    public void execute(DelegateExecution execution) {
        Integer authorId = (Integer) execution.getVariable("authorId");
        Long count = bookRepository.countBookEntitiesByAuthorId(authorId);

        execution.setVariable("count", count);
    }
}
