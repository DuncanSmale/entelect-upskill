package com.entelect.upskill.library.camunda;

import com.entelect.upskill.library.aggregation.BookCount;
import com.entelect.upskill.library.repository.AuthorRepository;
import com.entelect.upskill.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@RequiredArgsConstructor
@Named
public class RequestAuthorBookCountLessThanOrEqualToOne implements JavaDelegate {

    private final AuthorRepository authorRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Integer authorId = (Integer) execution.getVariable("authorId");

        authorRepository.deleteById(authorId);

        execution.setVariable("path", "LESS THAN OR EQUAL TO");
    }
}
