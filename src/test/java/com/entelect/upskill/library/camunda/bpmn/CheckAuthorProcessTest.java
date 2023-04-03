package com.entelect.upskill.library.camunda.bpmn;

import com.entelect.upskill.library.camunda.AddBookCount;
import com.entelect.upskill.library.camunda.DeleteAuthor;
import com.entelect.upskill.library.camunda.KeepAuthor;
import com.entelect.upskill.library.camunda.LogProcess;
import com.entelect.upskill.library.repository.AuthorRepository;
import com.entelect.upskill.library.repository.BookRepository;
import org.camunda.bpm.consulting.process_test_coverage.ProcessTestCoverage;
import org.camunda.bpm.engine.impl.persistence.entity.ProcessInstanceWithVariablesImpl;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.extension.junit5.test.ProcessEngineExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions.processEngine;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.runtimeService;
import static org.mockito.Mockito.when;

@ExtendWith({ProcessEngineExtension.class, MockitoExtension.class})
@Deployment(resources = {"request-author-book-count.bpmn"})
public class CheckAuthorProcessTest {

    private static final String ADD_BOOK_COUNT = "addBookCount";
    private static final String DELETE_AUTHOR = "deleteAuthor";
    private static final String KEEP_AUTHOR = "keepAuthor";
    private static final String LOG_PROCESS = "logProcess";
    private static final String PROCESS_KEY = "request-author-book-count";

    private static final Long BOOK_COUNT_GREATER = 3L;
    private static final Long BOOK_COUNT_ZERO = 0L;
    private static final Integer AUTHOR_ID = 1;
    @RegisterExtension
    ProcessEngineExtension extension = ProcessEngineExtension.builder()
            .configurationResource("camunda.cfg.xml")
            .build();


    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setup() {
        BpmnAwareTests.init(extension.getProcessEngine());
        Mocks.register(ADD_BOOK_COUNT, new AddBookCount(bookRepository));
        Mocks.register(DELETE_AUTHOR, new DeleteAuthor(authorRepository));
        Mocks.register(KEEP_AUTHOR, new KeepAuthor());
        Mocks.register(LOG_PROCESS, new LogProcess());


    }

    @Test
    @DisplayName("Given an author book count is grater than 1, " +
            "when a request is made to check the author, " +
            "then I expect the author is kept")
    void checkRequestAuthorBookCountGreaterPath() {

        //Given
        when(bookRepository.countBookEntitiesByAuthorId(AUTHOR_ID)).thenReturn(BOOK_COUNT_GREATER);

        //When
        ProcessInstanceWithVariablesImpl processInstance = (ProcessInstanceWithVariablesImpl)
                runtimeService().createProcessInstanceByKey(PROCESS_KEY)
                        .setVariable("authorId", AUTHOR_ID)
                        .execute();

        //Then
        assertThat(processInstance).hasPassedInOrder(
                ADD_BOOK_COUNT,
                KEEP_AUTHOR,
                LOG_PROCESS
        );

        assertThat(processInstance).isEnded();

        ProcessTestCoverage.calculate(processInstance, processEngine());
    }

    @Test
    @DisplayName("Given an author book count is less than or equal to 1, " +
            "when a request is made to check the author, " +
            "then I expect the author is deleted")
    void checkRequestAuthorBookCountLessThanPath() {

        //Given
        when(bookRepository.countBookEntitiesByAuthorId(AUTHOR_ID)).thenReturn(BOOK_COUNT_ZERO);

        //When
        ProcessInstanceWithVariablesImpl processInstance = (ProcessInstanceWithVariablesImpl)
                runtimeService().createProcessInstanceByKey(PROCESS_KEY)
                        .setVariable("authorId", AUTHOR_ID)
                        .execute();

        //Then
        assertThat(processInstance).hasPassedInOrder(
                ADD_BOOK_COUNT,
                DELETE_AUTHOR,
                LOG_PROCESS
        );

        assertThat(processInstance).isEnded();

        ProcessTestCoverage.calculate(processInstance, processEngine());
    }
}
