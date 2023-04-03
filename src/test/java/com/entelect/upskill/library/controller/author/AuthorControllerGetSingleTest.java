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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class AuthorControllerGetSingleTest extends AbstractControllerTest {

    @Autowired
    private PersonProperties testConfiguration;

    @MockBean
    private AuthorRepository authorRepository;

    @Override
    protected void mockClientResponse() {
        when(authorRepository.findById(anyInt())).thenReturn(Optional.ofNullable(testConfiguration.getAuthors().get(0)));
    }

    protected String getUri() {
        return "http://localhost/author/1";
    }

    @Override
    protected String stubRequestAsString() throws JsonProcessingException {
        return null;
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
                        get(getUri()).contentType(MediaType.APPLICATION_JSON)
                                .headers(new HttpHeaders()))
                .andExpect(status().isOk())
                .andReturn();
    }
}