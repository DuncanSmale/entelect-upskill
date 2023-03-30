package com.entelect.upskill.library.camunda;

import com.entelect.upskill.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@RequiredArgsConstructor
@Named
public class GetAuthorBookCount implements JavaDelegate {
    private final BookRepository bookRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Integer authorId = (Integer) execution.getVariable("authorId");
        Long count = bookRepository.countBookEntitiesByAuthorId(authorId);

        execution.setVariable("count", count);
    }
}
