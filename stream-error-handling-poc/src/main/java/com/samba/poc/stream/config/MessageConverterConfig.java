package com.samba.poc.stream.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConverterConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
//
    @Bean
    public CustomJacksonMessageConverter jsonConverter() {
        return new CustomJacksonMessageConverter();
    }
}
