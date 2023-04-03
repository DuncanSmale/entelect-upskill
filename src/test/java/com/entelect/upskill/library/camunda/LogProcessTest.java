package com.entelect.upskill.library.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class LogProcessTest {

    @Mock
    private DelegateExecution delegateExecution;

    @InjectMocks
    private LogProcess cut;

    @Test
    @DisplayName("Given a request, " +
            "when the execution method is invoked, " +
            "then the set variable method was called 3 times")
    void checkExecutionGetVariableIsCalledThreeTime() {
        // Given
        when(delegateExecution.getVariable("authorId")).thenReturn(1);
        when(delegateExecution.getVariable("path")).thenReturn("path");
        when(delegateExecution.getVariable("count")).thenReturn(1L);

        // When
        cut.execute(delegateExecution);

        // Then
        verify(delegateExecution, times(1)).getVariable("authorId");
        verify(delegateExecution, times(1)).getVariable("path");
        verify(delegateExecution, times(1)).getVariable("count");
    }
}