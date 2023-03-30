package com.entelect.upskill.library.camunda;

import com.entelect.upskill.library.repository.AuthorRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RequestAuthorBookCountGreaterThanOneTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private DelegateExecution delegateExecution;

    @InjectMocks
    private RequestAuthorBookCountGreaterThanOne cut;
    @Test
    @DisplayName("Given a request, " +
            "when the execute method is invoked, " +
            "then the set variable method is called once")
    void checkAuthorBookCount() throws Exception {
        // Given
        when(delegateExecution.getVariable(anyString())).thenReturn(1);

        // When
        cut.execute(delegateExecution);

        // Then
        verify(delegateExecution, times(1)).getVariable(anyString());

    }
}