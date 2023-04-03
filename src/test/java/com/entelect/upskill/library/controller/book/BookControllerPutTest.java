package com.entelect.upskill.library.controller.book;

import com.entelect.upskill.library.common.AbstractControllerTest;
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
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("testing")
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerPutTest extends AbstractControllerTest {

    @Autowired
    private PersonProperties testConfiguration;
    @MockBean
    private BookRepository bookRepository;

    @Override
    protected void mockClientResponse() {
        when(bookRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);
    }

    protected String getUri() {
        return "http://localhost/book/1";
    }

    protected String stubRequestAsString() throws JsonProcessingException {
        BookDTO stubBook = new BookDTO();
        stubBook.setAuthorId(1);
        stubBook.setTitle(testConfiguration.getBooks().get(0).getTitle());
        stubBook.setISBN(testConfiguration.getBooks().get(0).getISBN());
        stubBook.setPublisher(testConfiguration.getBooks().get(0).getPublisher());
        stubBook.setPublishedDate(testConfiguration.getBooks().get(0).getPublishedDate());
        return jsonMapper.writeValueAsString(stubBook);
    }

    protected void verifyResponse(MvcResult result) throws IOException {
        BookDTO response = jsonMapper.readValue(result.getResponse().getContentAsString(), BookDTO.class);
        assertNotNull(response);
        assertEquals("Happy Peter and the Wizard of Escabar", response.getTitle());
        assertEquals("Penguin Books", response.getPublisher());
        assertEquals(LocalDate.of(2021, 1, 3), response.getPublishedDate());
        assertEquals("0-2487-9445-0", response.getISBN());
        assertEquals(1, response.getAuthorId());
    }

    @Override
    protected MvcResult performRestRequest() throws Exception {
        return mockMvc.perform(
                        put(getUri()).contentType(MediaType.APPLICATION_JSON)
                                .content(stubRequestAsString())
                                .headers(new HttpHeaders()))
                .andExpect(status().isOk())
                .andReturn();
    }


}