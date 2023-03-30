package com.entelect.upskill.library.controller;

import com.entelect.upskill.library.dtos.AuthorDTO;
import com.entelect.upskill.library.repository.AuthorRepository;
import com.entelect.upskill.properties.PersonProperties;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

        assertEquals(2, response.length);

        assertEquals("Peter", response[0].getFirstName());
        assertEquals("Ryan", response[0].getLastName());
        assertEquals("South Africa", response[0].getCountryOfResidence());
        assertEquals("p@r.com", response[0].getEmailAddress());
        assertFalse(response[0].isDeleted());

        assertEquals("Dave", response[1].getFirstName());
        assertEquals("Martin", response[1].getLastName());
        assertEquals("UK", response[1].getCountryOfResidence());
        assertEquals("d@m.com", response[1].getEmailAddress());
        assertFalse(response[1].isDeleted());
    }
}