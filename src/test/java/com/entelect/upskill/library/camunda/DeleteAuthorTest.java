package com.entelect.upskill.library.camunda;

import com.entelect.upskill.library.repository.AuthorRepository;
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
class DeleteAuthorTest {
    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private DelegateExecution delegateExecution;

    @InjectMocks
    private DeleteAuthor cut;

    @Test
    @DisplayName("Given a request, " +
            "when the execute method is invoked, " +
            "then the set variable method is called once")
    void checkAuthorBookCount() {
        // Given
        when(delegateExecution.getVariable("authorId")).thenReturn(1);

        // When
        cut.execute(delegateExecution);

        // Then
        verify(delegateExecution, times(1)).getVariable("authorId");
        verify(authorRepository, times(1)).deleteById(1);
    }

}