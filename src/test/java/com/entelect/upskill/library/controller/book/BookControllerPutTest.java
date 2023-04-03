package com.entelect.upskill.library.controller.book;

import com.entelect.upskill.library.dtos.BookDTO;
import com.entelect.upskill.library.repository.BookRepository;
import com.entelect.upskill.properties.PersonProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("testing")
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerPutTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PersonProperties testConfiguration;
    @MockBean
    private BookRepository bookRepository;

    private String getUri() {
        return "http://localhost/book/1";
    }

    @BeforeEach
    void setup() {
        objectMapper.findAndRegisterModules();
    }

    @Test
    @DisplayName("Given a REST request, " +
            "when the REST call is successful, " +
            "then I expect the response to be correct")
    public void testSuccessfulResponse() throws Exception {
        // Given
        mockRepositoryBehaviour();

        // When
        MvcResult result = mockMvc.perform(
                        put(getUri()).contentType(MediaType.APPLICATION_JSON)
                                .content(stubRequestAsString())
                                .headers(new HttpHeaders()))
                .andExpect(status().isOk())
                .andReturn();

        // Then
        assertNotNull(result);
        verifyResponse(result);
    }

    private void mockRepositoryBehaviour() {
        when(bookRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);
    }

    private String stubRequestAsString() throws JsonProcessingException {
        BookDTO stubBook = new BookDTO();
        stubBook.setAuthorId(1);
        stubBook.setTitle(testConfiguration.getBooks().get(0).getTitle());
        stubBook.setISBN(testConfiguration.getBooks().get(0).getISBN());
        stubBook.setPublisher(testConfiguration.getBooks().get(0).getPublisher());
        stubBook.setPublishedDate(testConfiguration.getBooks().get(0).getPublishedDate());
        stubBook.setDeleted(testConfiguration.getBooks().get(0).isDeleted());
        return objectMapper.writeValueAsString(stubBook);
    }


    private void verifyResponse(MvcResult result) throws IOException {
        BookDTO response = objectMapper.readValue(result.getResponse().getContentAsString(), BookDTO.class);
        assertNotNull(response);
        assertEquals("Happy Peter and the Wizard of Escabar", response.getTitle());
        assertEquals("Penguin Books", response.getPublisher());
        assertEquals("2021-01-03", response.getPublishedDate());
        assertEquals("0-2487-9445-0", response.getISBN());
        assertEquals("false", response.isDeleted());
        assertEquals(1, response.getAuthorId());
    }


}