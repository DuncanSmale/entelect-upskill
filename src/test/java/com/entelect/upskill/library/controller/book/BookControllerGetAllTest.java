package com.entelect.upskill.library.controller.book;

import com.entelect.upskill.library.common.AbstractControllerTest;
import com.entelect.upskill.library.dtos.BookDTO;
import com.entelect.upskill.library.repository.BookRepository;
import com.entelect.upskill.properties.PersonProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("testing")
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerGetAllTest extends AbstractControllerTest {

    @Autowired
    private PersonProperties testConfiguration;
    @MockBean
    private BookRepository bookRepository;

    @Override
    protected void mockClientResponse() {
        when(bookRepository.findAll()).thenReturn(testConfiguration.getBooks());
    }

    protected String getUri() {
        return "http://localhost/book";
    }

    @Override
    protected String stubRequestAsString() throws JsonProcessingException {
        return null;
    }

    protected void verifyResponse(MvcResult result) throws IOException {
        BookDTO[] response = jsonMapper.readValue(result.getResponse().getContentAsString(), BookDTO[].class);
        assertNotNull(response);
        assertEquals("Happy Peter and the Wizard of Escabar", response[0].getTitle());
        assertEquals("Penguin Books", response[0].getPublisher());
        assertEquals(LocalDate.of(2021, 1, 3), response[0].getPublishedDate());
        assertEquals("0-2487-9445-0", response[0].getISBN());
        assertEquals(1, response[0].getAuthorId());
    }

    @Override
    protected MvcResult performRestRequest() throws Exception {
        return mockMvc.perform(
                        get(getUri()).contentType(MediaType.APPLICATION_JSON)
                                .headers(new HttpHeaders()))
                .andExpect(status().isOk())
                .andReturn();
    }
}

