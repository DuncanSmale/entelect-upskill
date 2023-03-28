package com.entelect.upskill.properties;

import com.entelect.upskill.concatenation.model.Person;
import com.entelect.upskill.library.model.AuthorEntity;
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
    private List<Person> people;
    private List<AuthorEntity> authors;
}
