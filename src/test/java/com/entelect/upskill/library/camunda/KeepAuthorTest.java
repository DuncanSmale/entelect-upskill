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


@ExtendWith(MockitoExtension.class)
class KeepAuthorTest {

    @Mock
    private DelegateExecution delegateExecution;

    @InjectMocks
    private KeepAuthor cut;

    @Test
    @DisplayName("Given a request, " +
            "when the execute method is invoked, " +
            "then the set variable method is called once")
    void checkAuthorBookCount() throws Exception {
        // Given and When
        cut.execute(delegateExecution);

        // Then
        verify(delegateExecution, times(1)).setVariable("path", "GREATER");
    }
}