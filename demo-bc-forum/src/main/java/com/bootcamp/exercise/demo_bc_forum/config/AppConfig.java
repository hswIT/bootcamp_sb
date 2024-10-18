package com.bootcamp.exercise.demo_bc_forum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

  @Bean(name = "JPHRestTemplate")
  RestTemplate restTemplateForJPH() {
    return new RestTemplate(); 
  }

}