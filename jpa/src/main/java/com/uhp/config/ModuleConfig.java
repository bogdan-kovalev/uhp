package com.uhp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author Bogdan Kovalev.
 */
@Configuration
public class ModuleConfig {
    /**
     * Bean Validation
     *
     * @return module
     */
    @Bean
    public KValidationModule validationModule() {
        return new KValidationModule();
    }
}
