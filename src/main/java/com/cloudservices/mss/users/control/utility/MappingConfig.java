package com.cloudservices.mss.users.control.utility;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingConfig {

    public MappingConfig(){}

    @Bean
    public ModelMapper mapper(){return new ModelMapper();}
}
