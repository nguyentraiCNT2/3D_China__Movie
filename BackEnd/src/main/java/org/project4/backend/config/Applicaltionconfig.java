package org.project4.backend.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// file cấu hình
@Configuration
public class Applicaltionconfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}