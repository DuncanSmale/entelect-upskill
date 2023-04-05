package com.entelect.upskill.controller.properties;


import com.entelect.upskill.controller.model.AuthorEntity;
import com.entelect.upskill.controller.model.BookEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties("upskill-configuration")
@Getter
@Setter
public class PersonProperties {

    private List<AuthorEntity> authors;
    private List<BookEntity> books;
}
