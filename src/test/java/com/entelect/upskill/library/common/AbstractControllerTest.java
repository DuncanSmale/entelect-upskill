package com.entelect.upskill.library.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith({SpringExtension.class})
@ActiveProfiles("testing")
@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbstractControllerTest {

    protected final ObjectMapper jsonMapper = new ObjectMapper();

    @Autowired
    protected MockMvc mockMvc;

    protected abstract void mockClientResponse();

    protected abstract String getUri();

    protected abstract String stubRequestAsString() throws JsonProcessingException;

    protected abstract void verifyResponse(MvcResult result) throws IOException;

    protected abstract MvcResult performRestRequest() throws Exception;


    @BeforeEach
    void setup() {
        jsonMapper.findAndRegisterModules();
    }

    @Test
    @DisplayName("Given a REST request, " +
            "when the REST call is successful, " +
            "then I expect the response to be correct")
    void testSuccessfulResponse() throws Exception {
        // Given
        mockClientResponse();

        // When
          MvcResult result = performRestRequest();

        // Then
        assertNotNull(result);
        verifyResponse(result);
    }
}
