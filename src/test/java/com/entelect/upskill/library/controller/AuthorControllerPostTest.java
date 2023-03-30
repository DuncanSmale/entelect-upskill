package com.entelect.upskill.library.controller;

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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("testing")
@SpringBootTest
@AutoConfigureMockMvc
class AuthorControllerPostTest {
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
                        post(getUri()).contentType(MediaType.APPLICATION_JSON)
                                .content(stubRequestAsString())
                                .headers(new HttpHeaders()))
                .andExpect(status().isCreated())
                .andReturn();

        // Then
        assertNotNull(result);
        verifyResponse(result);
    }

    private void mockRepositoryBehaviour() {
        when(authorRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);
    }

    private String stubRequestAsString() throws JsonProcessingException {
        AuthorDTO stubAuthor = new AuthorDTO();
        stubAuthor.setFirstName(testConfiguration.getAuthors().get(0).getFirstName());
        stubAuthor.setLastName(testConfiguration.getAuthors().get(0).getLastName());
        stubAuthor.setEmailAddress(testConfiguration.getAuthors().get(0).getEmailAddress());
        stubAuthor.setCountryOfResidence(testConfiguration.getAuthors().get(0).getCountryOfResidence());
        return objectMapper.writeValueAsString(stubAuthor);
    }


    private void verifyResponse(MvcResult result) throws IOException {
        AuthorDTO response = objectMapper.readValue(result.getResponse().getContentAsString(), AuthorDTO.class);
        assertNotNull(response);
        assertEquals("Peter", response.getFirstName());
        assertEquals("Ryan", response.getLastName());
        assertEquals("South Africa", response.getCountryOfResidence());
        assertEquals("p@r.com", response.getEmailAddress());
    }
}