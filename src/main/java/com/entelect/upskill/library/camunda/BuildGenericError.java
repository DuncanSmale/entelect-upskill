package com.entelect.upskill.library.camunda;

import lombok.extern.java.Log;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Log
@Named
public class BuildGenericError implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String errorLocation = (String) execution.getVariable("errorLocation");
        String errorMessage = (String) execution.getVariable("errorMessage");

        String errorLog = String.format("Error occurred at: %s, with message: %s", errorLocation, errorMessage);
        log.warning(errorLog);
    }
}
