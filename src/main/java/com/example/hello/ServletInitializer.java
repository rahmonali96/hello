package com.example.hello;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Developed by Rahmonali Yoqubov
 * Email: rahmonaliyoqubov@gmail.com
 * Date: 04.08.2022
 * Project: hello
 */
public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(HelloApplication.class);
    }
}
