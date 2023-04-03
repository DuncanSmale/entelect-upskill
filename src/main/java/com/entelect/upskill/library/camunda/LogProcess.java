package com.entelect.upskill.library.camunda;

import lombok.extern.java.Log;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Log
@Named
public class LogProcess implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        Integer authorId = (Integer) execution.getVariable("authorId");
        Long bookCount = (Long) execution.getVariable("count");
        String path = (String) execution.getVariable("path");

        String logMessage = String.format("Author: %d, has %d books, and took path %s", authorId, bookCount, path);
        log.info(logMessage);
    }
}
