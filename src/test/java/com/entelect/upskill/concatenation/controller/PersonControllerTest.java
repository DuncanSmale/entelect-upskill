package com.entelect.upskill.concatenation.controller;

import com.entelect.upskill.concatenation.model.ConcatenatedPersonDTO;
import com.entelect.upskill.concatenation.model.Person;
import com.entelect.upskill.properties.PersonProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("testing")
@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonProperties personConfiguration;

    private String getUri() {
        return "http://localhost/concatenate";
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

        // When
        MvcResult result = mockMvc.perform(
                        post(getUri()).contentType(MediaType.APPLICATION_JSON)
                                .content(stubRequestAsString())
                                .headers(new HttpHeaders()))
                .andExpect(status().isOk())
                .andReturn();

        // Then
        assertNotNull(result);
        verifyResponse(result);
    }

    private String stubRequestAsString() throws JsonProcessingException {
        Person stubPerson = new Person();
        stubPerson.setName(personConfiguration.getPeople().get(0).getName());
        stubPerson.setAge(personConfiguration.getPeople().get(0).getAge());
        return objectMapper.writeValueAsString(stubPerson);
    }

    private void verifyResponse(MvcResult result) throws IOException {
        ConcatenatedPersonDTO response = objectMapper.readValue(result.getResponse().getContentAsString(), ConcatenatedPersonDTO.class);
        assertNotNull(response);
    }


}