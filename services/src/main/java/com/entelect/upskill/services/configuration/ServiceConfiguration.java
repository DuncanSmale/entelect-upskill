package com.entelect.upskill.services.configuration;

import com.entelect.upskill.services.common.RestTemplateCaller;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties
@ComponentScan(basePackages = ServiceConfiguration.SERVICES_MODULE_CLASSPATH)
public class ServiceConfiguration {
    static final String SERVICES_MODULE_CLASSPATH = "com.entelect.upskill.services";

    @Bean
    @Primary
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder();
    }

    @Bean
    @Primary
    public RestTemplate restTemplate() {
        return restTemplateBuilder().build();
    }

    @Bean
    @Primary
    public RestTemplateCaller getRestTemplateCaller(RestTemplate restTemplate) {
        return new RestTemplateCaller(restTemplate);
    }
}
