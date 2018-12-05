package com.flamel.documents.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class ApiErrorConfig {

    @Bean
    public MessageSource apiErrorMessageSource() {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/api_errors_pt-BR");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }
}
