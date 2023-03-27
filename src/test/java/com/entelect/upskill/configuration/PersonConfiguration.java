package com.entelect.upskill.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "person")
@Getter
@Setter
public class PersonConfiguration {
    private String name;
    private int age;

}
