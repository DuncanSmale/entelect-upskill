package com.entelect.upskill.library.camunda;

import com.entelect.upskill.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@RequiredArgsConstructor
@Named
public class RequestAuthorBookCountGreaterThanOne implements JavaDelegate {

    private final BookRepository bookRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Integer authorId = (Integer) execution.getVariable("authorId");

        execution.setVariable("path", "GREATER");
    }
}
