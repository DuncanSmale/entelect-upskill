package com.entelect.upskill.library.mapper;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class AuthorMapperContextConfiguration {
    @Bean
    public AuthorMapper authorMapper() {
        return new AuthorMapperImpl();
    }
}
