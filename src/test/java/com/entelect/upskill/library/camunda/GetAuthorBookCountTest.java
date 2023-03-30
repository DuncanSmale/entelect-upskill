package com.entelect.upskill.library.camunda;

import com.entelect.upskill.library.repository.BookRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class GetAuthorBookCountTest {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private DelegateExecution delegateExecution;

    @InjectMocks
    private GetAuthorBookCount cut;

    @Test
    @DisplayName("Given a request, " +
            "when the execute method is invoked, " +
            "then execution and repository methods are called once")
    public void getAuthorCount() throws Exception {
        // Given
        mockExecutionProcess();


        // When
        cut.execute(delegateExecution);

        // Then
        verify(delegateExecution, times(1)).setVariable(anyString(), anyLong());
        verify(bookRepository, times(1)).countBookEntitiesByAuthorId(anyInt());
    }

    private void mockExecutionProcess() {
        when(bookRepository.countBookEntitiesByAuthorId(anyInt())).thenReturn(1L);
        when(delegateExecution.getVariable(anyString())).thenReturn(1);
    }


}