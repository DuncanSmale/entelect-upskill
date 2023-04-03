package com.entelect.upskill.library.configuration;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = DataProviderConfiguration.DATA_MODULE_CLASSPATH)
@ComponentScan(basePackages = DataProviderConfiguration.DATA_MODULE_CLASSPATH)
@NoArgsConstructor
public class DataProviderConfiguration {
    static final String DATA_MODULE_CLASSPATH = "com.entelect.upskill.library.repository";
}
