package com.entelect.upskill.library.controller.author;

import com.entelect.upskill.library.common.AbstractControllerTest;
import com.entelect.upskill.library.dtos.AuthorDTO;
import com.entelect.upskill.library.repository.AuthorRepository;
import com.entelect.upskill.properties.PersonProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthorControllerPutTest extends AbstractControllerTest {
    @Autowired
    private PersonProperties testConfiguration;

    @MockBean
    private AuthorRepository authorRepository;

    @Override
    protected void mockClientResponse() {
        when(authorRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);
    }

    protected String getUri() {
        return "http://localhost/author/1";
    }


    protected String stubRequestAsString() throws JsonProcessingException {
        AuthorDTO stubAuthor = new AuthorDTO();
        stubAuthor.setFirstName(testConfiguration.getAuthors().get(0).getFirstName());
        stubAuthor.setLastName(testConfiguration.getAuthors().get(0).getLastName());
        stubAuthor.setEmailAddress(testConfiguration.getAuthors().get(0).getEmailAddress());
        stubAuthor.setCountryOfResidence(testConfiguration.getAuthors().get(0).getCountryOfResidence());
        return jsonMapper.writeValueAsString(stubAuthor);
    }


    protected void verifyResponse(MvcResult result) throws IOException {
        AuthorDTO response = jsonMapper.readValue(result.getResponse().getContentAsString(), AuthorDTO.class);
        assertNotNull(response);
        assertEquals("Peter", response.getFirstName());
        assertEquals("Ryan", response.getLastName());
        assertEquals("South Africa", response.getCountryOfResidence());
        assertEquals("p@r.com", response.getEmailAddress());
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