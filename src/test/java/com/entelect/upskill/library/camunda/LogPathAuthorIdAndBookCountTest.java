package com.entelect.upskill.library.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
class LogPathAuthorIdAndBookCountTest {

    @Mock
    private DelegateExecution delegateExecution;

    @InjectMocks
    private LogPathAuthorIdAndBookCount cut ;

    @Test
    @DisplayName("Given a request, " +
            "when the execution method is invoked, " +
            "then the set variable method was called 3 times")
    void checkExecutionGetVariableIsCalledThreeTime() throws  Exception {
        // Given
        when(delegateExecution.getVariable("authorId")).thenReturn(1);
        when(delegateExecution.getVariable("path")).thenReturn("path");
        when(delegateExecution.getVariable("count")).thenReturn(1L);

        // When
        cut.execute(delegateExecution);

        // Then
        verify(delegateExecution, times(3)).getVariable(anyString());
    }
}