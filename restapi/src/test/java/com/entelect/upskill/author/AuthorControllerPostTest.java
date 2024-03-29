package com.entelect.upskill.author;

import com.entelect.entities.Author;
import com.entelect.upskill.properties.PersonProperties;
import com.entelect.upskill.repository.AuthorRepository;
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
        Author stubAuthor = new Author();
        stubAuthor.setFirstName(testConfiguration.getAuthors().get(0).getFirstName());
        stubAuthor.setLastName(testConfiguration.getAuthors().get(0).getLastName());
        return objectMapper.writeValueAsString(stubAuthor);
    }


    private void verifyResponse(MvcResult result) throws IOException {
        Author response = objectMapper.readValue(result.getResponse().getContentAsString(), Author.class);
        assertNotNull(response);
        assertEquals("Peter", response.getFirstName());
    }
}