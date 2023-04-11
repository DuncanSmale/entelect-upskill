package com.entelect.upskill.book;

import com.entelect.entities.Book;
import com.entelect.upskill.properties.PersonProperties;
import com.entelect.upskill.repository.BookRepository;
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
class BookControllerGetAllTest {
        private final ObjectMapper objectMapper = new ObjectMapper();
        @Autowired
        private MockMvc mockMvc;
        @Autowired
        private PersonProperties testConfiguration;
        @MockBean
        private BookRepository bookRepository;

        private String getUri() {
            return "http://localhost/book";
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
            when(bookRepository.findAll()).thenReturn(testConfiguration.getBooks());
        }

        private void verifyResponse(MvcResult result) throws IOException {
            Book[] response = objectMapper.readValue(result.getResponse().getContentAsString(),  Book[].class);
            assertNotNull(response);
            assertEquals("Happy Peter and the Wizard of Escabar", response[0].getTitle());
        }
    }

