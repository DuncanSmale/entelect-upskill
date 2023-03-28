package com.entelect.upskill.library.controller;

import com.entelect.upskill.concatenation.model.ConcatenatedPersonDTO;
import com.entelect.upskill.concatenation.model.Person;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("testing")
@SpringBootTest
@AutoConfigureMockMvc
class AuthorControllerGetAllTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonProperties testConfiguration;

    @MockBean
    private AuthorRepository authorRepository;

    private String getUri() {
        return "http://localhost/author";
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
                        get(getUri()).contentType(MediaType.APPLICATION_JSON)
                                .headers(new HttpHeaders()))
                .andExpect(status().isOk())
                .andReturn();

        // Then
        assertNotNull(result);
        verifyResponse(result);
    }

    private void mockRepositoryBehaviour() {
        when(authorRepository.findAll()).thenReturn(testConfiguration.getAuthors());
    }

    private void verifyResponse(MvcResult result) throws IOException {
        AuthorDTO[] response = objectMapper.readValue(result.getResponse().getContentAsString(), AuthorDTO[].class);
        assertNotNull(response);
        assertEquals("Peter", response[0].getFirstName());
    }
}