package com.example.hello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Developed by Rahmonali Yoqubov
 * Email: rahmonaliyoqubov@gmail.com
 * Date: 18.08.2022
 * Project: hello
 */
@Configuration
public class AppConfig {
    @Bean
    WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://www.google.com/search")
                .build();
    }
}
