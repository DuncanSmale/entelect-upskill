package com.entelect.upskill.library.controller.author;

import com.entelect.upskill.library.common.AbstractControllerTest;
import com.entelect.upskill.library.dtos.AuthorDTO;
import com.entelect.upskill.library.repository.AuthorRepository;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthorControllerGetAllTest extends AbstractControllerTest {

    @Autowired
    private PersonProperties testConfiguration;

    @MockBean
    private AuthorRepository authorRepository;

    @Override
    protected void mockClientResponse() {
        when(authorRepository.findAll()).thenReturn(testConfiguration.getAuthors());
    }

    protected String getUri() {
        return "http://localhost/author";
    }

    @Override
    protected String stubRequestAsString() throws JsonProcessingException {
        return null;
    }


    protected void verifyResponse(MvcResult result) throws IOException {
        AuthorDTO[] response = jsonMapper.readValue(result.getResponse().getContentAsString(), AuthorDTO[].class);
        assertNotNull(response);

        assertEquals(2, response.length);

        assertEquals("Peter", response[0].getFirstName());
        assertEquals("Ryan", response[0].getLastName());
        assertEquals("South Africa", response[0].getCountryOfResidence());
        assertEquals("p@r.com", response[0].getEmailAddress());

        assertEquals("Dave", response[1].getFirstName());
        assertEquals("Martin", response[1].getLastName());
        assertEquals("UK", response[1].getCountryOfResidence());
        assertEquals("d@m.com", response[1].getEmailAddress());
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